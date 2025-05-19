package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.CompanyDAO;
import util.DBUtil;

public class CompanyLogic {
	public static Company attendanceLogin(String companyId , String attendPass) throws SQLException{
		Company company = null;
		try {
			Connection con = DBUtil.getConnection();
			ResultSet rs = CompanyDAO.findCompanyById(companyId,attendPass,con);
			
			//事業所ID及びパスワードが一致したならcompanyをnewする
			if(rs.next()) {
				company = new Company(rs.getString("id"), rs.getString("company_name"), rs.getString("clock_pass"), rs.getString("admin_pass"));				
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return company;
	}
}
