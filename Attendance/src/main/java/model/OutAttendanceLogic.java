package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.AttendanceViewDAO;
import util.DBUtil;

public class OutAttendanceLogic {
	public static List<AttendanceViewDTO> createView(String companyId) throws Exception{
		try(Connection con = DBUtil.getConnection()) {
			return AttendanceViewDAO.findAllAttendanceViewByIds(companyId, con);
		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		}
		
	}
}
