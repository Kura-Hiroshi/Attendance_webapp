package test.java.test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import dao.CompanyDAO;
import dao.NextIdNumDAO;
import model.Company;
import model.CompanyLogic;
import util.DBUtil;

class CreateAccountTest {

	@Test
	void test() {
		String id = "D001";
		String name = "株式会社テストカンパニー";
		String passForAttend = "1234";
		String passForAdmin ="1234";
		
		Connection con;
		try {
			con = DBUtil.getConnection();
			try {
				CompanyLogic.accountCreate(id, name, passForAttend, passForAdmin);
				Company companyA = new Company(id, name, passForAttend, passForAdmin);
				Company companyB = CompanyDAO.findCompanyById(id, con);
				
				assertEquals(companyA, companyB);
				assertEquals(1, NextIdNumDAO.getNextId(id, con));
			} catch (Exception e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
				
	}

}
