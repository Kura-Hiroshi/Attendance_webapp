package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import dao.AttendanceDAO;
import dao.EmployeeDAO;

public class AttendanceLogic {
	public static String execute(int employeeId,String companyId,String pass, String eventType) throws SQLException {
		String msg = null;
		try {
			Class.forName("org.h2.Driver");
			
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("ドライバのロードに失敗しました");
		}
		
		Connection con = null;
		try {
			
			con = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Desktop/勤怠管理アプリ/Attendance_webapp/Attendance/DB/AttendanceDB", "sa", "");

			Employee employee = EmployeeDAO.findEmployeeById(employeeId, companyId,pass, con);
			
			if(employee != null) {
				AttendanceDAO.insert(employee.getCompanyId(), employee.getId(), eventType, con);				
			}else {
				msg = "従業員名もしくはパスワードが違います。";
				}
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return msg;
	}
}
