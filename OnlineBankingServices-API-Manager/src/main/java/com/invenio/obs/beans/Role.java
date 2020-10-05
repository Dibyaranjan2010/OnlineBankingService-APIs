package com.invenio.obs.beans;

public class Role {
	
	//class attributes
	
	private int roleId;
	private String userRole;
	private String userDescription;
	
	//toString 
	
	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", userRole=" + userRole
				+ ", userDescription=" + userDescription + "]";
	}

	//getter and setter

	public int getRoleId() {
		return roleId;
	}



	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}



	public String getUserRole() {
		return userRole;
	}



	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}



	public String getUserDescription() {
		return userDescription;
	}



	public void setUserDescription(String userDescription) {
		this.userDescription = userDescription;
	}

	//constructor using superclass

	public Role() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructor using field

	public Role(int roleId, String userRole, String userDescription) {
		super();
		this.roleId = roleId;
		this.userRole = userRole;
		this.userDescription = userDescription;
	}
	
	
}
