package com.invenio.obs.daos;



import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.invenio.obs.beans.Customer;
import com.invenio.obs.interfaces.AdminDaoInterface;



@Repository
public class AdminDao implements AdminDaoInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;


	/*
	 * private int count=0; public int getCount() { return count; } public void
	 * setCount(int count) { this.count = count; }
	 * 
	 * 
	 * 
	 * 
	 * //TODO CUSTOMER ID GENERATOR int customerIdGenerator() { return count++;
	 * 
	 * }
	 * 
	 * 
	 * public void getLastId() { String sql="SELECT max(customerId) FROM customer";
	 * int n=jdbcTemplate System.out.println(n); setCount(n);
	 * 
	 * System.out.println(n); }
	 */
	@Override
	public String createCustomerAccount(Customer customer) {
		// TODO Auto-generated method stub
		/*
		 * customer.setCustomerId(customerIdGenerator());
		 * System.out.println(customerIdGenerator());
		 */
		String sql="insert into customer value (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int n=jdbcTemplate.update(sql,customer.getCustomerAccountNumber(),customer.getCustomerId(),customer.getCustomerPin(),customer.getCustomerFirstName(),
				customer.getCustomerMiddleName(),customer.getCustomerLastName(),customer.getCustomerUserName()
				,customer.getCustomerEmailId(),customer.getCustomerDob(),customer.getCustomerAddress(),
				customer.getCustomerPassword(),customer.getCustomerPhoneNumber(),customer.getCustomerAdhaarNumber()
				,customer.getCustomerPanNumber(),customer.getCustomerAccountType(),customer.getCustomerOwnership(),
				customer.getCustomerBranch(),customer.getCustomerIfscCode(),customer.getCustomerBalance());
		
		if(n==1)
		{
			return ("Customer Account Successfully Created with "+" Customer Name:"+customer.getCustomerFirstName() +" and Customer Account Number:"+customer.getCustomerAccountNumber());
		}
		else
		{
			return ("Something went Wrong,Please Try Again!");
		}
	}


	@Override
	public String deleteCustomerAccount(int accountNumber) {
		// TODO Auto-generated method stub

		String sql="DELETE FROM customer WHERE customerAccountNumber= ? ";
		int n3=0;
		try {
			n3= jdbcTemplate.update(sql,  accountNumber);
		}catch(Exception e)
		{
			return "Error deleting Account";
		}
		if(n3>=1)
			return "Successfully deleted account and customer details.";
		else
			return "Something went Wrong,Please Try Again!!";

	}

	@Override
	public String updateCustomerEmailAccount(int customerAccountNumber ,String customerEmailId) {
		// TODO Auto-generated method stub
		String sql="update customer set customerEmailId= ?  where customerAccountNumber=? ";
		int r= jdbcTemplate.update(sql,customerEmailId,customerAccountNumber);
		if(r==1)
		{
			return "Update successfully Done";
		}
		else
		{
			return "Something went wrong,please try again!";
		}

	}

	@Override
	public String updateCustomerPhoneAccount(int customerAccountNumber ,String customerPhoneNumber) {
		// TODO Auto-generated method stub
		String sql="update customer set customerPhoneNumber= ?  where customerAccountNumber=? ";
		int r= jdbcTemplate.update(sql,customerPhoneNumber,customerAccountNumber);
		if(r==1)
		{
			return "Update successfully Done";
		}
		else
		{
			return "Something went wrong,please try again!";
		}

	}

	@Override
	public String updateCustomerAddressAccount(int customerAccountNumber ,String customerAddress) {
		// TODO Auto-generated method stub
		String sql="update customer set customerAddress= ?  where customerAccountNumber=? ";
		int r= jdbcTemplate.update(sql,customerAddress,customerAccountNumber);
		if(r==1)
		{
			return "Update successfully Done";
		}
		else
		{
			return "Something went wrong,please try again!";
		}

	}

	@Override
	public Customer viewCustomerDetails(int accountNumber) {
		String sql="select * from customer where customerAccountNumber= ? ";

		return jdbcTemplate.queryForObject(sql, new Object[] {accountNumber}, new BeanPropertyRowMapper<Customer>(Customer.class));



	}







}
