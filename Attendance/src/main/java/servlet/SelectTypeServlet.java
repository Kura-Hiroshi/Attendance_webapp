package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import model.Company;
import model.Employee;
import model.EmployeeLogic;

/**
 * Servlet implementation class SelectTypeServlet
 */
@WebServlet("/SelectTypeServlet")
public class SelectTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/selectType.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//セッションスコープからCompanyインスタンスを取得
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("company");
		
		request.setCharacterEncoding("UTF-8");
		String eventType = request.getParameter("action");
		
		String msg = null;//クライアントに返すメッセージ用の変数
		List<Employee> employeeList = new ArrayList<Employee>();
		
		//選択リストを作るためにデータベースから従業員データを取得する
		try {
			employeeList = EmployeeLogic.getAllemployee(company.getId());
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		
		
		
		
		//リクエストスコープに保存する
		
		request.setAttribute("eventType", eventType);
		request.setAttribute("employeeList",employeeList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/attendance.jsp");
		dispatcher.forward(request, response);
		
	}

}
