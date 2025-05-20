package model;

import java.io.Serializable;

public class Company implements Serializable{
	private String id;
	private String companyName;
	private String attendPass;
	private String adminPass;
	
	
	public Company(String id, String companyName, String attendPass, String adminPass) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.attendPass = attendPass;
		this.adminPass = adminPass;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getAttendPass() {
		return attendPass;
	}


	public void setAttendPass(String attendPass) {
		this.attendPass = attendPass;
	}


	public String getAdminPass() {
		return adminPass;
	}


	public void setAdminPass(String adminPass) {
		this.adminPass = adminPass;
	}


	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName + ", attendPass=" + attendPass + ", adminPass="
				+ adminPass + "]";
	}
	
	
}
