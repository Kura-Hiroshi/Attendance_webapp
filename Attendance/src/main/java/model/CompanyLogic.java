package model;

import java.sql.Connection;

import dao.CompanyDAO;
import util.DBUtil;

public class CompanyLogic {
    public static Company attendanceLogin(String companyId , String attendPass) throws Exception {
        try (Connection con = DBUtil.getConnection()) {
            return CompanyDAO.findCompanyById(companyId, attendPass, con);
        }catch (Exception e){
        	throw new Exception(e.getMessage());
        }
    }
}
