package servlet;

import java.io.BufferedReader;
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
import model.EmployeeLogic;

/**
 * Servlet implementation class EmployeeDeleteServlet
 */
@WebServlet("/EmployeeDeleteServlet")
public class EmployeeDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper objectMapper = new ObjectMapper();
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/employeeDelete.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = null;//クライアントに返すメッセージ用の変数
		
		//事業所インスタンスをセッションスコープから取得
		HttpSession session = request.getSession();
		Company company = new Company("xxxxcompany","xxxx株式会社","1234","1234");//開発中のもの
		session.setAttribute("company", company);//開発中のもの
		company = (Company)session.getAttribute("company");
		
		//JSONデータを取得する
		JsonNode jsonNode;
        try (BufferedReader reader = request.getReader()) {
            jsonNode = objectMapper.readTree(reader);
        }
        
        int employeeId = jsonNode.has("employee_id")?jsonNode.get("employee_id").asInt():0;
		
		
		//データベースを操作する
        int r = 0;
		try {
			r = EmployeeLogic.deleteEmployee(employeeId, company.getId());
		} catch (Exception e) {
			msg = e.getMessage();
		}
        
		Map<String, Object> data = new HashMap<>();
		
		//戻り値で成功失敗の判定をして、分岐
		if(r>0 && msg == null) {
			//削除が成功した場合、成功を知らせる
			data.put("success", true);
			objectMapper.writeValue(response.getWriter(), data);
		}else if(msg != null){
			//エラーメッセージが存在する場合
			data.put("success", false);
			data.put("msg",msg);
		    objectMapper.writeValue(response.getWriter(), data);
		}else {
			//rが０、つまり何も削除しなかった場合。
			msg = "削除する対象が存在しません";
			data.put("success", false);
			data.put("msg",msg);
		    objectMapper.writeValue(response.getWriter(), data);
		}
		
		
	}

}
