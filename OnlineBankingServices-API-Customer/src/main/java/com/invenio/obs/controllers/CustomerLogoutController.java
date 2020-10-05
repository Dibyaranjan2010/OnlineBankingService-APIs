package com.invenio.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import com.invenio.obs.interfaces.CustomerDaoInterface;

@RestController
public class CustomerLogoutController {

	@Autowired
	private CustomerDaoInterface customerDaoInterface;
	//TODO Login
		@GetMapping("/customer/logout")
		public String logoutCustomer() {
			
			boolean status=customerDaoInterface.logoutCustomer();
			if(status==true)	{
				return "LOGOUT SUCCESSFULLY\nWE HOPE YOU LIKE OUR SERVICES\n*****THANK YOU****";
				
			}
			else {
				return "Please Login Again";
			}
		}
}
