package model;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import dao.EmployeeDAO;
import dao.NextIdNumDAO;
import util.DBUtil;



/**
 *EMPLOYEEテーブルにかかわる操作をまとめるロジック層のクラス 
 */
public class EmployeeLogic {
	/** 従業員を登録するメソッド 
	 */
	public static Employee registerEmployee(Company company,String employeeName,String pass) throws Exception {
		Connection con = null;
		try {
			con = DBUtil.getConnection();
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		
		try {
			
			//トランザクションを開始する
			con.setAutoCommit(false);
			//次の従業員IDを取得する
			int nextId = NextIdNumDAO.getNextId(company.getId(), con);
			
			//新しい従業員情報を登録する
			EmployeeDAO.insert(nextId,company.getId(), employeeName, pass, con);
			
			//コミットする
			con.commit();
			
			
			//Employeeインスタンスを返す
			return new Employee(nextId, company.getId(), employeeName, pass);
			
			
			
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
			con.rollback();
		}finally {
			if(con != null)
				try {
					con.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
		}
		return null;
	}
	
	public static int deleteEmployee(int employeeId,String companyId) throws Exception {
		try(Connection con = DBUtil.getConnection()){
			try {
				con.setAutoCommit(false);
				
				int r = EmployeeDAO.delete(employeeId, companyId, con);
				
				con.commit();
				return r;
			}catch(Exception e){
				e.printStackTrace();
				con.rollback();
				throw new SQLException(e.getMessage());
			}
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static List<Employee>  getAllemployee(String companyId) throws Exception{
		try(Connection con = DBUtil.getConnection()){
				
				return EmployeeDAO.findAllEmployee(companyId, con);
				
			
		}catch(Exception e) {
			throw new Exception(e.getMessage());
		}
	}
	
	public static String getEmployee(String companyId ,int employeeId ,String pass) throws Exception{
		try(Connection con = DBUtil.getConnection()){
			
			Employee employee =  EmployeeDAO.findEmployeeById(employeeId,companyId ,pass, con);
			if(employee==null) {
				return "従業員名もしくはパスワードが違います。";
			}
			return null;
			
		
	}catch(Exception e) {
		throw new Exception(e.getMessage());
	}
	}
	
}
