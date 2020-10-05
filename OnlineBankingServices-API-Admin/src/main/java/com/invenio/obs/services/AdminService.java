package com.invenio.obs.services;

import org.springframework.stereotype.Service;

import com.invenio.obs.beans.Customer;
import com.invenio.obs.interfaces.AdminServiceInterface;
@Service
public class AdminService implements AdminServiceInterface {

	@Override
	public String createCustomerAccount(Customer customer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteCustomerAccount(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCustomerEmailAccount(int accountNumber,String customerEmailId) {
		// TODO Auto-generated method stub
		return null;
	}
		
	
	@Override
	public String updateCustomerPhoneAccount(int accountNumber, String customerPhoneNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateCustomerAddressAccount(int accountNumber, String customerAddress) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer viewCustomerDetails(int accountNumber) {
		// TODO Auto-generated method stub
		return null;
	}


}
