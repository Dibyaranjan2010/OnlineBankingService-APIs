package com.invenio.obs.controllers;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.invenio.obs.beans.Customer;
import com.invenio.obs.beans.Statement;
import com.invenio.obs.beans.Transaction;
import com.invenio.obs.interfaces.ManagerDaoInterface;



@RestController
public class ManagerRestController {

	@Autowired
	ManagerDaoInterface managerDaoInterface;

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello OnlineBankingServices-API-MANAGER";
	}


	@GetMapping("/customerAccountDetails/{accountNumber}")
	public String viewCustomerAccountDetails(@PathVariable("accountNumber")int accountNumber){
		Customer c= managerDaoInterface.viewCustomerAccountDetails(accountNumber);
		if(c==null) {
			return "NO DATA FOUND";
			
		}
		else {
			return c.toString();
		}
	}

	
	
	@RestController
	public class ViewStatementOfCustomer {
		@GetMapping("/manager/customerViewStatement/{customerAccountNumber}/{startDate}/{endDate}")
		public List<Transaction> customerStatement(@PathVariable("customerAccountNumber") String customerAccountNumber, @PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate)
		{
			String url="http://localhost:8082/customer/viewStatement/{customerAccountNumber}/{startDate}/{endDate}"; //8082
			Map<String,String> uriVariables=new HashMap<String,String>();
			uriVariables.put("customerAccountNumber", customerAccountNumber);
			uriVariables.put("startDate", startDate);
			uriVariables.put("endDate", endDate);
			RestTemplate restTemplate=new RestTemplate();
			 Statement statement=restTemplate.getForObject(url, Statement.class, uriVariables);
			
			return statement.getStatement();
		}
	}

}
