package model;

import java.io.Serializable;

public class Employee implements Serializable {
	private String companyId;
	private int id;
	private String name;
	private String pass;
	public Employee(int id, String companyId, String name, String pass) {
		super();
		this.companyId = companyId;
		this.id = id;
		this.name = name;
		this.pass = pass;
	}
	
	
	
	public Employee( int id,String companyId) {
		super();
		this.companyId = companyId;
		this.id = id;
	}



	public Employee(int id,String companyId,  String pass) {
		super();
		this.companyId = companyId;
		this.id = id;
		this.pass = pass;
	}



	public String getCompanyId() {
		return companyId;
	}
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}



	@Override
	public String toString() {
		return "Employee [companyId=" + companyId + ", id=" + id + ", name=" + name + ", pass=" + pass + "]";
	}
	
	
	
}
