package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.AttendanceViewDTO;

public class AttendanceViewDAO {
	public static List<AttendanceViewDTO> findAllAttendanceViewByIds(String companyId,Connection con) throws SQLException {
		List<AttendanceViewDTO> list = new ArrayList<AttendanceViewDTO>();
		
		String sql = """
				SELECT * FROM ATTENDANCE_VIEW
				WHERE company_id = ?;
				""";
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, companyId);

            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
            	AttendanceViewDTO atvDTO = new AttendanceViewDTO(rs.getString("company_id"), rs.getInt("employee_id"), rs.getString("employee_name"), rs.getDate("clock_date"), rs.getTimestamp("work_in"), rs.getTimestamp("work_out"));
            	list.add(atvDTO);
            }
            
            return list;
            
		}catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("勤怠記録の取得に失敗しました");
		}
	}
}
