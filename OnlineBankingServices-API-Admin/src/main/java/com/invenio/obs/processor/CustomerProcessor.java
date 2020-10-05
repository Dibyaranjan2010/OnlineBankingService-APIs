package com.invenio.obs.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.invenio.obs.beans.Customer;



public class CustomerProcessor implements ItemProcessor<Customer, Customer> {

  private static final Logger log = LoggerFactory.getLogger(CustomerProcessor.class);

@Override
public Customer process(Customer customer) throws Exception {
	// TODO Auto-generated method stub
	log.info("Started processing the csv file..");
	final int customerAccountNumber=customer.getCustomerAccountNumber();
	final int customerId=customer.getCustomerId();
	final int customerPin=customer.getCustomerPin();
	final String customerFirstName=customer.getCustomerFirstName().toUpperCase();
	final String  customerMiddleName=customer.getCustomerMiddleName().toUpperCase();
	final String customerLastName= customer.getCustomerLastName().toUpperCase();
	final String customerUserName=customer.getCustomerUserName();		
    final String customerEmailId= customer.getCustomerEmailId();
    final String customerDob= customer.getCustomerDob();
    final String customerAddress= customer.getCustomerAddress();
    final String customerPassword= customer.getCustomerPassword();
    final String customerPhoneNumber= customer.getCustomerPhoneNumber();
    final String customerAdhaarNumber= customer.getCustomerAdhaarNumber();
    final String customerPanNumber=customer.getCustomerPanNumber();
    final String customerAccountType= customer.getCustomerAccountType();
    final String customerOwnership=customer.getCustomerOwnership();
    final String customerBranch= customer.getCustomerBranch();
    final String customerIfscCode= customer.getCustomerIfscCode();
    final Double customerBalance=customer.getCustomerBalance();
	    final Customer transformedCustomer = new Customer(customerAccountNumber,customerId,customerPin,customerFirstName,customerMiddleName,customerLastName,customerUserName,customerEmailId,customerDob,customerAddress,customerPassword,customerPhoneNumber,customerAdhaarNumber,customerPanNumber,customerAccountType,customerOwnership,customerBranch,customerIfscCode,customerBalance);
	    log.info("Converting (" + customer + ") into (" + transformedCustomer + ")");
		return transformedCustomer;
}

}