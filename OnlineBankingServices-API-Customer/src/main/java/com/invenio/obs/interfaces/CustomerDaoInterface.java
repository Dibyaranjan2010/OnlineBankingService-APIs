package com.invenio.obs.interfaces;




import java.util.List;

import com.invenio.obs.beans.Customer;
import com.invenio.obs.beans.Transaction;

public interface CustomerDaoInterface {

	String loginCustomer(int customerId, String customerPassword);

	String forgetPassword(String customerEmailId,int customerPin);

	int updatePassword( String existingPassword, String customerUpdatePassword);

	double viewAccountBalance();

	boolean logoutCustomer();

	Customer viewAccountDetails();

	List<Transaction> viewStatement(String startDate, String endDate);

	String downloadStatement(String startDate, String endDate);

	String withdrawMoney(double amount);

	String depositMoney(double amount);

	String transactMoney(int recAccountNumber, double amount);

	List<Transaction> viewCustomerStatement(int customerAccountNumber, String startDate, String endDate);
	

}
