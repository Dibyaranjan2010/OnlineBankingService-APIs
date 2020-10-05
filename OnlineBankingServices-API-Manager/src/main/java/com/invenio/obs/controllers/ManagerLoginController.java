package com.invenio.obs.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


import com.invenio.obs.interfaces.ManagerDaoInterface;

@RestController
public class ManagerLoginController {

	@Autowired
	private ManagerDaoInterface managerDaoInterface;
	//TODO Login
		@GetMapping("/login/{userId}/{userPassword}")
		public String loginManager(@PathVariable("userId")int userId,@PathVariable("userPassword")String userPassword) {
			
			String loginName=managerDaoInterface.loginManager(userId,userPassword);
			if(loginName==null) {
				return "ERROR: INVALID USER ID OR PASSWORD";
			}
			else {
				
				return "SUCCESS: WELCOME "+loginName;

			}
		}
}
