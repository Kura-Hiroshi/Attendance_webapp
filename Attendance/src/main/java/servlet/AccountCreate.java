package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Company;
import model.CompanyLogic;
import model.PasswordHasher;

/**
 * Servlet implementation class AccountCreate
 */
@WebServlet("/AccountCreate")
public class AccountCreate extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  ObjectMapper mapper = new ObjectMapper();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/accountCreate.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JsonNode json = mapper.readTree(request.getReader());
		
		request.setCharacterEncoding("UTF-8");
		String companyId = json.has("company_id")?json.get("company_id").asText().trim():null;
		String companyName = json.has("company_name")?json.get("company_name").asText().trim():null;
		String passForAttend = json.has("pass_for_attend")?json.get("pass_for_attend").asText().trim():null;
		String passForAdmin = json.has("pass_for_admin")?json.get("pass_for_admin").asText().trim():null;
		
		Company company = null;
		String msg = null;//クライアントに送信するメッセージ用変数
		
		
		//バリデーションチェック
		if (!model.Validator.isValidCompanyId(companyId)) {
			msg = "登録できる事業所IDは8文字以上50文字以下の半角英数字です";
		} else if(!model.Validator.isValidCompanyName(companyName)) {
			msg ="登録できる事業所の名前は1文字以上100文字以下です";
		} else if(!model.Validator.isValidPassword(passForAttend)) {
			msg = "パスワードに使える文字は8文字以上100文字以下の半角英数字です";
		}else if(!model.Validator.isValidPassword(passForAdmin)) {
			msg = "パスワードに使える文字は8文字以上100文字以下の半角英数字です";
		}
		
		if(msg != null) {
			//この時点でmsgにメッセージが格納されている場合にはデータベース処理はしない。
			try {
				//パスワードのハッシュ化
				String hashPassForAttend = PasswordHasher.hashPassword(passForAttend);
				String hashPassForAdmin = PasswordHasher.hashPassword(passForAdmin);

				
				//データベースへの登録を行う。
				company = CompanyLogic.accountCreate(companyId, companyName, hashPassForAttend, hashPassForAdmin);
			} catch (Exception e) {
				msg = e.getMessage();
			}
		}
		
		response.setContentType("application/json; charset=UTF-8");
		HttpSession session = request.getSession();
		Map<String, Object> data = new HashMap<>();
		
		if(company != null && msg == null) {
			//登録に成功した場合、セッションにcompanyを保存する。
			session.setAttribute("admin", company);
			data.put("success", true);
		    mapper.writeValue(response.getWriter(), data);
		}else if(msg == null) {
			msg = "登録に失敗しました。";
			data.put("success", false);
			data.put("msg",msg);
		    mapper.writeValue(response.getWriter(), data);
		}else {
			//登録に失敗した場合、メッセージを返す
			data.put("success", false);
			data.put("msg",msg);
		    mapper.writeValue(response.getWriter(), data);
		}
	}

}
