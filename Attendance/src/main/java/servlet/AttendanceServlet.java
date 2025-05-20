package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.SQLException;
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

import model.AttendanceLogic;
import model.Company;
import model.Employee;

/**
 * Servlet implementation class AttendanceServlet
 */
@WebServlet("/AttendanceServlet")
public class AttendanceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper objectMapper = new ObjectMapper();
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/attendance.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープからCompanyインスタンスを取得
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("company");
		
		String msg = null;//クライアントに返すメッセージ用の変数
		
		//フォームの入力値を取得する
		JsonNode jsonNode;
        try (BufferedReader reader = request.getReader()) {
            jsonNode = objectMapper.readTree(reader);
        }
        System.out.println(company);
        int employeeId = jsonNode.has("employee_id") ? Integer.valueOf(jsonNode.get("employee_id").asInt()) : 0;
        String pass =jsonNode.has("pass") ? jsonNode.get("pass").asText() : null;
		Employee employee = new Employee(employeeId, company.getId(),pass);
		System.out.println(employee);
		String eventType = jsonNode.has("event_type") ? jsonNode.get("event_type").asText():null; 
        
		//データベースへの登録処理
		try {
			msg = AttendanceLogic.execute(employeeId,company.getId(),pass, eventType);
		} catch (SQLException e) {
			e.printStackTrace();
			msg = "登録に失敗しました";
		}
        
		System.out.println(msg);
		
		//登録処理の結果に応じて分岐する
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		if(msg == null) {

		        // JSONとしてクライアントに返す
			Map<String, Object> data = new HashMap<>();
			data.put("success", true);
		    objectMapper.writeValue(response.getWriter(), data);
		}else {
			Map<String, Object> data = new HashMap<>();
			data.put("success", false);
			data.put("msg", msg);
		    objectMapper.writeValue(response.getWriter(), data);
		}
		
		
	}

}
