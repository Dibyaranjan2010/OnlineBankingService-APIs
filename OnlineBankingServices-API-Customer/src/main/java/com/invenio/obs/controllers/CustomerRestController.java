package com.invenio.obs.controllers;




import java.util.List;

//import java.sql.SQLIntegrityConstraintViolationException;
//
//import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.web.bind.annotation.PutMapping;

import org.springframework.web.bind.annotation.RestController;


import com.invenio.obs.beans.Customer;
import com.invenio.obs.beans.Statement;
import com.invenio.obs.beans.Transaction;
import com.invenio.obs.interfaces.CustomerDaoInterface;


@RestController
public class CustomerRestController {


	@Autowired
	private CustomerDaoInterface customerDaoInterface;

	//TODO say hello() :GET map
	@GetMapping("/customer")
	public String sayHello()
	{
		return "Welcome to BANK";
	}



	//TODO forgot Password
    @GetMapping("/customer/forgetPassword/{customerEmailId}/{customerPin}")
    public String forgetPassword(@PathVariable("customerEmailId")String customerEmailId,@PathVariable("customerPin")int customerPin) {
        String auto_password=customerDaoInterface.forgetPassword(customerEmailId,customerPin);

        if(auto_password.equals("INVALID_EMAIL")) {
            return "ERROR: INVALID CUSTOMER E-MAIL ID ";
        }

        if(auto_password.equals("INVALID")) {
            return "ERROR: INVALID CREDENTIALS ";
        }
        else if(auto_password.equals("ERROR")) {
            return "ERROR: SERVER DOWN !! CAN'T SET PASSWORD NOW !! TRY LATER";
        }
        else {
            return "PASSWORD GOT GENERATED : " +auto_password;
        }
        
    }


	//TODO update password
	@PutMapping("/customer/updatePassword/{existingPassword}/{customerUpdatePassword}")
	public String updatePassword(@PathVariable("existingPassword")String existingPassword,@PathVariable("customerUpdatePassword")String customerUpdatePassword) {
		int n=customerDaoInterface.updatePassword(existingPassword,customerUpdatePassword);
		if(n==703) {
			return "ERROR : INVALID CUSTOMER PASSWORD ";
		}
		else if(n==1) {
			return "SUCCESS: PASSWORD UPDATED";
		}
		else if(n==909) {
			return "ERROR: PLEASE LOGIN FIRST";
		}

		return "MESSAGE: CAN'T UPDATE PASSWORD!! TRY LATER";
	}

	//TODO view balance
	@GetMapping("/customer/viewBalance")
	public String viewAccountBalance() {
		double balance=customerDaoInterface.viewAccountBalance();
		if(balance==-909.0) {
			return "ERROR: PLEASE LOGIN FIRST";
		}
		else {
			return "YOUR BALANCE IS :"	+balance;
		}
	}

	//TODO View Account Details
	@GetMapping("/customer/accountDetails")
	public String viewAccountDetails() {
		Customer details=customerDaoInterface.viewAccountDetails();
		if(details==null) {
			return "PLEASE LOGIN FIRST";
		}
		else if(details.getCustomerAccountNumber()==-999) {
			return "ERROR: SERVER DOWN !! TRY LATER";
		}
		else {
			return details.toAccountDetails();
		}

	}

	@GetMapping("/customer/viewStatement/{startDate}/{endDate}")
	public List<Transaction> viewStatement(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate){
		List<Transaction> transactionList= customerDaoInterface.viewStatement(startDate,endDate);

		if(transactionList==null) {
			return null;
		}
		else {
			return transactionList;
		}
	}



	@GetMapping("/customer/downloadStatement/{startDate}/{endDate}")
	public String downloadStatement(@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate) {
		String location=customerDaoInterface.downloadStatement(startDate,endDate);
		if(location.equals("NO_LOGIN")) {
			return "ERROR: PLEASE LOGIN FIRST";
		} else if(location.length()>=10){
			return "SUCCESS: THE FILE HAS BEEN DOWNLOADED\nLOCATION: "+location;
		}
		else {
			return "ERROR: CAN'T DOWNLOAD FILE NOW!! TRY LATER";
		}
	}

	@GetMapping("/customer/withdraw/{amount}")
	public String withdrawMoney(@PathVariable("amount")double amount) {
		String status=customerDaoInterface.withdrawMoney(amount);
		switch(status) {
		case("No_LOGIN"): return "PLEASE LOGIN FIRST";
		case("ERROR_FETCH"):return "CAN'T PROCEED TRANSACTION!! TRY LATER!";
		case("NOT_ENOUGH_MONEY"):return "MESSAGE: LOW BALANCE \nCAN'T PROCEED TRANSACTION";
		case("SUCCESSFUL"):return "SUCCESS: WITHDRAWN SUCCESSFUL";
		default :return "MESSAGE: SORRY FOR INCOVINIENCE!!\nTRY AGAIN LATER";
		}
	}

	@GetMapping("/customer/deposit/{amount}")
	public String depositMoney(@PathVariable("amount")double amount) {
		String status=customerDaoInterface.depositMoney(amount);
		switch(status) {
		case("No_LOGIN"): return "PLEASE LOGIN FIRST";
		case("ERROR_FETCH"):return "CAN'T PROCEED TRANSACTION!! TRY LATER!";
		case("EXCESS_AMOUNT"):return "MESSAGE: CAN'T TRANSACT MORE THAN 1LAKH AT A TIME";
		case("SUCCESSFUL"):return "SUCCESS: DEPOSIT SUCCESSFUL";
		default :return "MESSAGE: SORRY FOR INCOVINIENCE!!\nTRY AGAIN LATER";
		}
	}

	@GetMapping("/customer/transaction/{recipentAccountNumber}/{amount}")
	public String transactMoney(@PathVariable("recipentAccountNumber")int recAccountNumber,@PathVariable("amount")double amount) {
		String status=customerDaoInterface.transactMoney(recAccountNumber,amount);
		switch(status) {
		case("No_LOGIN"): return "PLEASE LOGIN FIRST";
		case("ERROR_FETCH"):return "CAN'T PROCEED TRANSACTION!! TRY LATER!";
		case("INVALID"):return "ERROR: INVALID RECIPIENT ACCOUNT NUMBER";
		case("NOT_ENOUGH_MONEY"): return "MESSAGE: LOW BALANCE \\nCAN'T PROCEED TRANSACTION";
		case("EXCESS_AMOUNT"):return "MESSAGE: CAN'T TRANSACT MORE THAN 1LAKH AT A TIME";
		case("SUCCESSFUL"):return "SUCCESS: TRANSFERED SUCCESSFULLY";
		case("ERROR700"):return "ERROR: COULDN'T PROCEED TRANSACTION !!\nMONEY NOT DEBITED FROM YOUR ACCOUNT";
		case("ERROR701"):return " CAN'T COMPLETE THE TRANSACTION\\nTHE MONEY IS REFUNDED TO YOUR ACCOUNT";
		case("ERROR702"):return "CAN'T COMPLETE THE TRANSACTION\\nMONEY GOT DEBITED FROM YOUR ACCOUNT\\nIT WILL BE REFUNDED WITHIN 1 WORKING DAY\\n\\n*****SORRY FOR THE INCONVIENCE***";
		case("ERROR703"):return "ERROR OCCURED IN RECIPIENT BANK\\nTHE MONEY IS REFUNDED TO YOUR ACCOUNT";
		case("LAST_RETURN"): return "LAST RETURN EXECUTED";
		default :return "MESSAGE: SORRY FOR INCOVINIENCE!!\nTRY AGAIN LATER";
		}
	}
	
	@GetMapping("/customer/viewStatement/{customerAccountNumber}/{startDate}/{endDate}")
	public Statement viewCustomerStatement(@PathVariable("customerAccountNumber") int customerAccountNumber,@PathVariable("startDate") String startDate,@PathVariable("endDate") String endDate){
		List<Transaction> transactionList= customerDaoInterface.viewCustomerStatement(customerAccountNumber,startDate,endDate);
		Statement statement=new Statement();
		if(transactionList==null) {
			return null;
		}
		else {
			statement.setStatement(transactionList);
			return statement;
		}
	}
}












