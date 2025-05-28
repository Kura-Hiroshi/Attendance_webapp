package model;

import java.io.Serializable;
import java.util.Objects;



/**
 *事業所クラス
 *このクラスは事業所１件分を表します。 
 */
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


	@Override
	public int hashCode() {
		return Objects.hash(adminPass, attendPass, companyName, id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Company other = (Company) obj;
		return Objects.equals(adminPass, other.adminPass) && Objects.equals(attendPass, other.attendPass)
				&& Objects.equals(companyName, other.companyName) && Objects.equals(id, other.id);
	}
	
	
}
