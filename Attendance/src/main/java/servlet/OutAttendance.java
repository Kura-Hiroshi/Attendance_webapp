package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import model.AttendanceViewDTO;
import model.Company;
import model.CreateExcelFile;
import model.OutAttendanceLogic;

/**
 * Servlet implementation class OutAttendanceOnExcelServlet
 */
@WebServlet("/OutAttendance")
public class OutAttendance extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ObjectMapper objectMapper = new ObjectMapper()
			.registerModule(new JavaTimeModule())
			.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/outAttendance.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Company company = (Company)session.getAttribute("admin");
		String msg = null;//クライアントに返すメッセージ用の変数
		List<AttendanceViewDTO> attendanceList = null;
		
		//JSONデータを受け取る
		JsonNode json = objectMapper.readTree(request.getReader());
		
		request.setCharacterEncoding("UTF-8");
		String start = json.has("start")?json.get("start").asText():null;
		String end = json.has("end")?json.get("end").asText():null;
		Boolean exportExcel = json.has("exportExcel")?json.get("exportExcel").asBoolean():false;
		
		
		//勤怠記録をデータベースから取得する
		try {
			attendanceList = OutAttendanceLogic.createView(company.getId(),start,end);
		} catch (Exception e) {
			e.printStackTrace();
			msg = e.getMessage();
		}
		
		//exportExceがtrueの場合、Excelファイルをクライアントに書き出して終了する
		if(exportExcel) {
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		    response.setHeader("Content-Disposition", "attachment; filename=\"attendance.xlsx\"");
			try {
				CreateExcelFile.create(attendanceList,response.getOutputStream());
				return;
			}catch(Exception e) {
				msg = e.getMessage();
			}
			
		}
		System.out.println(msg);
		//登録処理の結果に応じて分岐する
        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
		if(attendanceList != null && msg == null) {
		        // JSONとしてクライアントに返す
			Map<String, Object> data = new HashMap<>();
			data.put("success", true);
			data.put("list", attendanceList);
		    objectMapper.writeValue(response.getWriter(), data);
		}else if(msg != null){
			Map<String, Object> data = new HashMap<>();
			data.put("success", false);
			data.put("msg", msg);
		    objectMapper.writeValue(response.getWriter(), data);
		}
	}

}
