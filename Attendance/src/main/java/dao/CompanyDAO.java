package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Company;

public class CompanyDAO {
    public static Company findCompanyById(String companyId, Connection con) throws SQLException {
        String sql = "SELECT * FROM COMPANY WHERE id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sql)) {
            pstmt.setString(1, companyId);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Company(
                        rs.getString("id"),
                        rs.getString("company_name"),
                        rs.getString("clock_pass"),
                        rs.getString("admin_pass")
                    );
                }
            }
        }catch (SQLException e) {
			throw new SQLException("ログインできませんでした。しばらくしてから再度お試しください。");
        }

        return null; // 一致しない場合
    }
    
    public static void insert(String companyId,String companyName,String passForAttend,String passForAdmin,Connection con) throws SQLException {
    	try {
			PreparedStatement pstmt = con.prepareStatement("""
					INSERT INTO COMPANY
					VALUES(?,?,?,?);
					""");
			pstmt.setString(1, companyId);
			pstmt.setString(2, companyName);
			pstmt.setString(3, passForAttend);
			pstmt.setString(4, passForAdmin);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLException();
		}
    }
}
