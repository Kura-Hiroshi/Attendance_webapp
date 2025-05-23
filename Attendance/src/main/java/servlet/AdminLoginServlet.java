package servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private  ObjectMapper mapper = new ObjectMapper();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/adminLogin.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		JsonNode json = mapper.readTree(request.getReader());
		
		request.setCharacterEncoding("UTF-8");
		String companyId = json.has("company_id")?json.get("company_id").asText():null;
		String pass = json.has("pass")?json.get("pass").asText():null;
		
		Company company = null;
		String msg = null;//クライアントに送信するメッセージ用変数
		
		try {
			//パスワードをハッシュ化する
			String hashPass = PasswordHasher.hashPassword(pass); 
			
			//データベースの事業所データと照合する
			company = CompanyLogic.adminLogin(companyId, hashPass);
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = response.getWriter();
		Map<String,Object> data = new HashMap<String, Object>(); 
		
		//事業所ID及びパスワードが一致したものが存在する場合としない場合で分岐
		
		if(msg != null) {
			//エラーメッセージがある場合
			data.put("success", false);
			data.put("msg", msg);
		}else if(company == null) {
			//存在しなかった場合
			msg = "事業所IDまたはパスワードが一致しません。";
			data.put("success", false);
			data.put("msg", msg);
		}else {
			//存在した場合
			HttpSession session = request.getSession();
			session.setAttribute("admin", company);
			data.put("success", true);
		}
		mapper.writeValue(out, data); 
		
		
	}

}
