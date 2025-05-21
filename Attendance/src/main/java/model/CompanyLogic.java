package model;

import java.sql.Connection;

import dao.CompanyDAO;
import util.DBUtil;

public class CompanyLogic {
    public static Company attendanceLogin(String companyId , String attendPass) throws Exception {
        try (Connection con = DBUtil.getConnection()) {
        	Company company =CompanyDAO.findCompanyById(companyId, con); 
            if(company.getAdminPass().equals(attendPass)) {
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
    		System.out.println(company.getAdminPass()+":"+adminPass);
            if(company.getAdminPass().equals(adminPass)) {
            	return company;
            }
        }catch (Exception e){
        	throw new Exception(e.getMessage());
        }
    	return null;
    }
}
