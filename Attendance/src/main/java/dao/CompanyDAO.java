package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;

public class CompanyDAO {
	public static ResultSet  findCompanyById(String companyId , String attendPass,Connection con) throws SQLException {
		ResultSet rs = null;
		try {
			con = DBUtil.getConnection();
			PreparedStatement pstmt = con.prepareStatement("""
					SELECT * FROM COMPANY WHERE id = ? AND clock_pass = ?;
					""");
			pstmt.setString(1, companyId);
			pstmt.setString(2, attendPass);
			rs =  pstmt.executeQuery();
			
			
		}catch(IllegalStateException e){
			throw new IllegalStateException(e.getMessage());
		}catch (SQLException e) {
			throw new SQLException("ログインできませんでした。しばらくしてから再度お試しください。");
		}finally {
			try {
				if(con != null) {con.close();}
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return rs;
	}
}
