package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;

public class EmployeeDAO {
	public static Employee findEmployeeById(int employeeId,String companyId,String pass,Connection con) throws SQLException{
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE COMPANY_ID = ? AND ID = ? AND PASS = ?");
			pstmt.setString(1, companyId);
			pstmt.setInt(2, employeeId);
			pstmt.setString(3, pass);
			ResultSet rs =  pstmt.executeQuery();
			if(rs.next()) {
				return new Employee(rs.getInt("id"), rs.getString("company_id"), rs.getString("employee_name"),rs.getString("pass"));
			}
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException("従業員データの取得に失敗しました");
		}
		return null;
		
		
	}
	
	public static List<Employee> findAllEmployee(String companyId,Connection con) throws SQLException{
		List<Employee> employeeList = new ArrayList<Employee>();
		
		try {
			PreparedStatement pstmt = con.prepareStatement("SELECT * FROM EMPLOYEE WHERE COMPANY_ID = ?");
			pstmt.setString(1, companyId);
			
			ResultSet rs =  pstmt.executeQuery();
			
			while(rs.next()) {
				employeeList.add(new Employee(rs.getInt("id"), rs.getString("company_id"), rs.getString("employee_name"),rs.getString("pass")));
			}
			
			return employeeList;
			
		}catch(SQLException e){
			e.printStackTrace();
			throw new SQLException("従業員データの取得に失敗しました");
		}
		
	}
	
	
	
	public static void insert(int id,String companyId,String employeeName,String pass,Connection con) throws SQLException {
		try {
			PreparedStatement pstmt = con.prepareStatement("""
					INSERT INTO EMPLOYEE
					VALUES(?,?,?,?)
					""");
			pstmt.setInt(1, id);
			pstmt.setString(2, companyId);
			pstmt.setString(3, employeeName);
			pstmt.setString(4,pass);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			throw new SQLException("従業員の登録に失敗しました。");
		}
	}
	
	public static int delete(int employeeId,String companyID,Connection con	) throws SQLException{
		try {
			PreparedStatement pstmt = con.prepareStatement("""
					DELETE FROM ATTENDANCE
					WHERE company_id =? AND employee_id = ?;
					DELETE FROM EMPLOYEE
					WHERE company_id =? AND id = ?;
					""");
			pstmt.setString(1, companyID);
			pstmt.setInt(2, employeeId);
			pstmt.setString(3, companyID);
			pstmt.setInt(4, employeeId);
			int r = pstmt.executeUpdate();
			return r;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new SQLException("データベースで従業員の削除に失敗しました。");
		}
	}
}
