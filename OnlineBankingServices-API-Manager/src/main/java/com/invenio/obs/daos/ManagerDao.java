package com.invenio.obs.daos;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.invenio.obs.beans.Customer;
import com.invenio.obs.interfaces.ManagerDaoInterface;



@Repository
public class ManagerDao implements ManagerDaoInterface {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	private int userId=0;

	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}

	//---------------------------------------------
	//TODO LOGIN
	@Override
	public String loginManager(int userId, String userPassword) {
		String name=null;
		try {
			String sql="SELECT firstName FROM user WHERE userId=? AND userPassword=?";
			name=jdbcTemplate.queryForObject(sql, new Object[] {userId,userPassword}, String.class);
		}catch(Exception e) {
			return null;
		}

		setUserId(userId);
		return name;

	}

	//TODO VIEW CUSTOMER ACCOUNT DETAILS
	@Override
	public Customer viewCustomerAccountDetails(int accountNumber) {
		Customer c=new Customer();
		Customer customer=new Customer();
		if(getUserId()==0)
		{
			return c;
		}
		else {
			String sql="select * from customer where customerAccountNumber= ? ";
			
			try {
				customer=jdbcTemplate.queryForObject(sql, new Object[] {accountNumber}, new BeanPropertyRowMapper<Customer>(Customer.class));
			}catch(Exception e) {
				return null;
			}
		}
		return customer;

	}


	//TODO LOGOUT MANAGER
	@Override
	public boolean logoutManager() {
		setUserId(0);
		if(getUserId()==0) {
			return true;
		}
		else {
			return false;
		}

	}











}
