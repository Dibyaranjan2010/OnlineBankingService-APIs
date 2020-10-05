package com.invenio.obs.interfaces;



import com.invenio.obs.beans.Customer;

public interface AdminServiceInterface {
	public String createCustomerAccount(Customer customer);
	public String deleteCustomerAccount(int accountNumber);
	public String updateCustomerEmailAccount(int accountNumber,String customerEmailId);
	public String updateCustomerPhoneAccount(int accountNumber,String customerPhoneNumber);
	public String updateCustomerAddressAccount(int accountNumber,String customerAddress);
	public Customer viewCustomerDetails(int accountNumber);
	//add from csv
}
