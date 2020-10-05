package com.invenio.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.invenio.obs.interfaces.CustomerDaoInterface;

@RestController
public class CustomerLoginController {

	@Autowired
	private CustomerDaoInterface customerDaoInterface;
	//TODO Login
		@GetMapping("/login/{customerId}/{customerPassword}")
		public String loginCustomer(@PathVariable("customerId")int customerId,@PathVariable("customerPassword")String customerPassword) {
			
			String loginName=customerDaoInterface.loginCustomer(customerId,customerPassword);
			if(loginName==null) {
				return "ERROR: INVALID USER ID OR PASSWORD";
			}
			else {
				
				return "SUCCESS: WELCOME "+loginName;

			}
		}
}
