package model;

import java.sql.Connection;
import java.sql.SQLException;

import dao.AttendanceDAO;
import dao.EmployeeDAO;
import util.DBUtil;


/**
 *ATTENDANCEテーブルへの操作を行うロジック層のクラス 
 */
public class AttendanceLogic {
	/** 従業員ID、事業所ID、従業員パスワード、出勤・退勤などの種別を受け取り、勤怠記録を行うDAOのメソッドを呼び出すメソッド。 */
	public static AttendanceDTO execute(int employeeId,String companyId,String pass, String eventType) throws SQLException {
		try {
			Class.forName("org.h2.Driver");
			
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException("ドライバのロードに失敗しました");
		}
		
		Connection con = null;
		try {
			
			con = DBUtil.getConnection();
			
			//勤怠記録テーブルに挿入する。
			
			Employee employee = EmployeeDAO.findEmployeeById(employeeId, companyId,pass, con);
			
			int r = AttendanceDAO.insert(employee.getCompanyId(), employee.getId(), eventType, con);				
			
			return AttendanceDAO.findbyId(r, con);
			
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
			
		}finally {
			if(con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
