package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.AttendanceDTO;

public class AttendanceDAO {
	public static int insert(String companyId,int employeeId,String eventType,Connection con) throws SQLException {
		try (PreparedStatement pstmt = con.prepareStatement("""
		        INSERT INTO ATTENDANCE(company_id, employee_id, clock_date, event_type, event_time)
		        VALUES (?, ?, current_date, ?, current_timestamp)
		        """, Statement.RETURN_GENERATED_KEYS)) {

		    pstmt.setString(1, companyId);
		    pstmt.setInt(2, employeeId);
		    pstmt.setString(3, eventType);
		    pstmt.executeUpdate();

		    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
		        if (generatedKeys.next()) {
		            return generatedKeys.getInt(1);
		        } else {
		            throw new SQLException("データの登録に成功したが、勤怠記録のidの取得に失敗しました。");
		        }
		    }

		} catch (SQLException e) {
		    e.printStackTrace();
		    throw new SQLException("データの登録に失敗しました");
		}

		
		
	}
	
	public static AttendanceDTO findbyId(int id ,Connection con) throws SQLException{
		try {
			PreparedStatement pstmt = con.prepareStatement("""
					SELECT * FROM ATTENDANCE
					WHERE id = ?;
					""");
			pstmt.setInt(1, id);
			ResultSet rs =  pstmt.executeQuery();
			AttendanceDTO attDTO = null;
			if(rs.next()) {
				attDTO = new AttendanceDTO(rs.getInt("id"), rs.getString("company_id"), rs.getInt("employee_id"), rs.getDate("clock_date"), rs.getString("event_type"), rs.getTimestamp("event_time"));
			}
			return attDTO;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("登録した時刻の取得に失敗しました");
		}
	}
}
