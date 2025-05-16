package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AttendanceDAO {
	public static void insert(String companyId,int employeeId,String eventType,Connection con) {
		try {
			PreparedStatement pstmt = con.prepareStatement("""
					INSERT INTO ATTENDANCE(company_id, employee_id, clock_date, event_type, event_time)
					VALUES(?,?,current_date,?,current_timestamp)
					""");
			pstmt.setString(1, companyId);
			pstmt.setInt(2, employeeId);
			pstmt.setString(3, eventType);
			int r = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
