package com.invenio.obs.interfaces;


import com.invenio.obs.beans.Customer;

public interface ManagerDaoInterface {

	
	public Customer viewCustomerAccountDetails(int accountNumber);

	public boolean logoutManager();

	public String loginManager(int userId, String userPassword);

	
}
