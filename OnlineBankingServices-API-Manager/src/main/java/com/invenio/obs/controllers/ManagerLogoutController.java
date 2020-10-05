package com.invenio.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;


import com.invenio.obs.interfaces.ManagerDaoInterface;

@RestController
public class ManagerLogoutController {

	@Autowired
	private ManagerDaoInterface managerDaoInterface;
	//TODO Login
		@GetMapping("/customer/logout")
		public String logoutCustomer() {
			
			boolean status=managerDaoInterface.logoutManager();
			if(status==true)	{
				return "LOGOUT SUCCESSFULLY";
				
			}
			else {
				return "Please Login Again";
			}
		}
}
