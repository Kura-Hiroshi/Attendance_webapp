package model;

import java.sql.Connection;

import dao.CompanyDAO;
import dao.NextIdNumDAO;
import util.DBUtil;

public class CompanyLogic {
    public static Company attendanceLogin(String companyId , String attendPass) throws Exception {
        try (Connection con = DBUtil.getConnection()) {
        	Company company =CompanyDAO.findCompanyById(companyId, con); 
            if(company.getAttendPass().equals(attendPass)) {
            	return company;
            }
        }catch (Exception e){
        	throw new Exception(e.getMessage());
        }
        return null;
    }
    
    public static Company adminLogin(String companyId , String adminPass) throws Exception {
    	try (Connection con = DBUtil.getConnection()) {
    		Company company =CompanyDAO.findCompanyById(companyId, con); 
            if(company.getAdminPass().equals(adminPass)) {
            	return company;
            }
        }catch (Exception e){
        	throw new Exception(e.getMessage());
        }
    	return null;
    }
    
    public static Company accountCreate(String companyId,String companyName,String passForAttend,String passForAdmin) throws Exception{
    	try (Connection con = DBUtil.getConnection()) {
    		Company company =CompanyDAO.findCompanyById(companyId, con);
            if(company == null) {
            	//既に登録済みのIDが存在しない場合
            	try {
            		con.setAutoCommit(false);
            		CompanyDAO.insert(companyId, companyName, passForAttend, passForAdmin, con);
            		NextIdNumDAO.insert(companyId, con);
            		con.commit();
            		
            		company = new Company(companyId, companyName, passForAttend, passForAdmin); 
            		return company;
				} catch (Exception e) {
					con.rollback();
					throw new Exception("事業所アカウントの登録に失敗しました");
				}
            }
        }catch (Exception e){
        	throw new Exception(e.getMessage());
        }
    	
    	
    	return null;
    }
}
