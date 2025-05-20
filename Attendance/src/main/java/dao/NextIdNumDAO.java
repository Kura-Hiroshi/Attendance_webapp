package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NextIdNumDAO {
	public static int getNextId(String companyId,Connection con) throws SQLException {
		try {
			PreparedStatement pstmt = con.prepareStatement("""
					SELECT next_employee_id FROM EMPLOYEE_NUM
					WHERE company_id = ?
					""");
			pstmt.setString(1, companyId);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				incrementNum(companyId, con);
				return rs.getInt("next_employee_id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException("従業員IDの次の番号の取得に失敗しました。");
		}
		return 0;
	}
	
	private static void incrementNum(String companyId,Connection con) throws SQLException {
		try {
			PreparedStatement pstmt = con.prepareStatement("""
					UPDATE EMPLOYEE_NUM
					SET next_employee_id = next_employee_id +1
					WHERE company_id = ?
					""");
			pstmt.setString(1, companyId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
