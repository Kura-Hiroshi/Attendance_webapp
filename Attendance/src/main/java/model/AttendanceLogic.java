package model;

import java.sql.Connection;
import java.sql.SQLException;

import dao.AttendanceDAO;
import dao.EmployeeDAO;
import util.DBUtil;

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
			
			con = DBUtil.getConnection();

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
