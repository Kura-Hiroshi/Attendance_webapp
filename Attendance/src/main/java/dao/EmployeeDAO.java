package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeDAO {
	public static ResultSet findEmployeeById(int employeeId,String companyId,String pass,Connection con) throws SQLException{
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE COMPANY_ID = ? AND ID = ? AND PASS = ?");
			pstmt.setString(1, companyId);
			pstmt.setInt(2, employeeId);
			pstmt.setString(3, pass);
			ResultSet rs =  pstmt.executeQuery();
			return rs;
		}catch(SQLException e){
			e.printStackTrace();
			return null;
		}
		
		
	}
}
