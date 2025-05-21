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
}
