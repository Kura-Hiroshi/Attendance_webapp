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
import model.Employee;
import model.EmployeeLogic;

/**
 * Servlet implementation class EmployeeRegisterServlet
 */
@WebServlet("/EmployeeRegisterServlet")
public class EmployeeRegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/employeeRegister.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String msg = null;//クライアントに返すメッセージ用の変数
		Employee employee = null;
		
		//事業所オブジェクトをセッションスコープから取得
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("admin");
		
		
		//クライアントからJSONを受け取る
		JsonNode jsonNode;
        try (BufferedReader reader = request.getReader()) {
            jsonNode = objectMapper.readTree(reader);
        }
        
        String name = jsonNode.has("name")?jsonNode.get("name").asText():null;
        String pass = jsonNode.has("pass")?jsonNode.get("pass").asText():null;
		
		//データベースへの登録を行う
		try {
			employee =  EmployeeLogic.registerEmployee(company, name, pass);
		} catch (Exception e) {
			msg = e.getMessage();
		}
		
		//バリデーションチェック
		if (model.Validator.isValidEmployeeName(name)) {
			msg = "登録できる従業員名は1文字以上50文字以下";
		}
		
		response.setContentType("application/json; charset=UTF-8");
		Map<String, Object> data = new HashMap<>();
		
		if(employee != null && msg == null) {
			//登録が成功した場合、id、名前、パスワードを返す
			data.put("success", true);
			data.put("id", employee.getId());
			data.put("name", employee.getName());
			data.put("pass", employee.getPass());
		    objectMapper.writeValue(response.getWriter(), data);
		}else if(msg == null) {
			msg = "登録に失敗しました。";
			data.put("success", false);
			data.put("msg",msg);
		    objectMapper.writeValue(response.getWriter(), data);
		}else {
			//登録に失敗した場合、メッセージを返す
			data.put("success", false);
			data.put("msg",msg);
		    objectMapper.writeValue(response.getWriter(), data);
		}
		
		
	}

}
