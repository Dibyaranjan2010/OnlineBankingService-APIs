package com.invenio.obs.beans;

public class Customer {

	private int customerAccountNumber, customerPin;
	private String customerId,customerFirstName, customerMiddleName, customerLastName, customerUserName, customerEmailId, customerDob, customerAddress, customerPassword, customerPhoneNumber,
	customerAdhaarNumber, customerPanNumber, customerAccountType, customerOwnership, customerBranch, customerIfscCode;
	private double customerBalance;
	
	
	public Customer(int customerAccountNumber, String customerId, int customerPin, String customerGender,String customerFirstName,
			String customerMiddleName, String customerLastName, String customerUserName, String customerEmailId,
			String customerDob, String customerAddress, String customerPassword, String customerPhoneNumber,
			String customerAdhaarNumber, String customerPanNumber, String customerAccountType, String customerOwnership,
			String customerBranch, String customerIfscCode, double customerBalance) {
		super();
		this.customerAccountNumber = customerAccountNumber;
		this.customerId = customerId;
		this.customerPin = customerPin;
		this.customerFirstName = customerFirstName;
		this.customerMiddleName = customerMiddleName;
		this.customerLastName = customerLastName;
		this.customerUserName = customerUserName;
		this.customerEmailId = customerEmailId;
		this.customerDob = customerDob;
		this.customerAddress = customerAddress;
		this.customerPassword = customerPassword;
		this.customerPhoneNumber = customerPhoneNumber;
		this.customerAdhaarNumber = customerAdhaarNumber;
		this.customerPanNumber = customerPanNumber;
		this.customerAccountType = customerAccountType;
		this.customerOwnership = customerOwnership;
		this.customerBranch = customerBranch;
		this.customerIfscCode = customerIfscCode;
		this.customerBalance = customerBalance;
	}


	public int getCustomerAccountNumber() {
		return customerAccountNumber;
	}


	public void setCustomerAccountNumber(int customerAccountNumber) {
		this.customerAccountNumber = customerAccountNumber;
	}


	public String getCustomerId() {
		return customerId;
	}


	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public int getCustomerPin() {
		return customerPin;
	}


	public void setCustomerPin(int customerPin) {
		this.customerPin = customerPin;
	}


	public String getCustomerFirstName() {
		return customerFirstName;
	}


	public void setCustomerFirstName(String customerFirstName) {
		this.customerFirstName = customerFirstName;
	}


	public String getCustomerMiddleName() {
		return customerMiddleName;
	}


	public void setCustomerMiddleName(String customerMiddleName) {
		this.customerMiddleName = customerMiddleName;
	}


	public String getCustomerLastName() {
		return customerLastName;
	}


	public void setCustomerLastName(String customerLastName) {
		this.customerLastName = customerLastName;
	}


	public String getCustomerUserName() {
		return customerUserName;
	}


	public void setCustomerUserName(String customerUserName) {
		this.customerUserName = customerUserName;
	}


	public String getCustomerEmailId() {
		return customerEmailId;
	}


	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}


	public String getCustomerDob() {
		return customerDob;
	}


	public void setCustomerDob(String customerDob) {
		this.customerDob = customerDob;
	}


	public String getCustomerAddress() {
		return customerAddress;
	}


	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}


	public String getCustomerPassword() {
		return customerPassword;
	}


	public void setCustomerPassword(String customerPassword) {
		this.customerPassword = customerPassword;
	}


	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}


	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}


	public String getCustomerAdhaarNumber() {
		return customerAdhaarNumber;
	}


	public void setCustomerAdhaarNumber(String customerAdhaarNumber) {
		this.customerAdhaarNumber = customerAdhaarNumber;
	}


	public String getCustomerPanNumber() {
		return customerPanNumber;
	}


	public void setCustomerPanNumber(String customerPanNumber) {
		this.customerPanNumber = customerPanNumber;
	}


	public String getCustomerAccountType() {
		return customerAccountType;
	}


	public void setCustomerAccountType(String customerAccountType) {
		this.customerAccountType = customerAccountType;
	}


	public String getCustomerOwnership() {
		return customerOwnership;
	}


	public void setCustomerOwnership(String customerOwnership) {
		this.customerOwnership = customerOwnership;
	}


	public String getCustomerBranch() {
		return customerBranch;
	}


	public void setCustomerBranch(String customerBranch) {
		this.customerBranch = customerBranch;
	}


	public String getCustomerIfscCode() {
		return customerIfscCode;
	}


	public void setCustomerIfscCode(String customerIfscCode) {
		this.customerIfscCode = customerIfscCode;
	}


	public double getCustomerBalance() {
		return customerBalance;
	}


	public void setCustomerBalance(double customerBalance) {
		this.customerBalance = customerBalance;
	}


	

	@Override
	public String toString() {
		return "Customer [customerAccountNumber=" + customerAccountNumber + ", customerId=" + customerId
				+ ",  customerFirstName=" + customerFirstName
				+ ", customerMiddleName=" + customerMiddleName + ", customerLastName=" + customerLastName
				+ ", customerUserName=" + customerUserName + ", customerEmailId=" + customerEmailId + ", customerDob="
				+ customerDob + ", customerAddress=" + customerAddress + ", customerPhoneNumber=" + customerPhoneNumber
				+ ", customerAdhaarNumber=" + customerAdhaarNumber + ", customerPanNumber=" + customerPanNumber
				+ ", customerAccountType=" + customerAccountType + ", customerOwnership=" + customerOwnership + ", customerBranch="
				+ customerBranch + ", customerIfscCode=" + customerIfscCode + ", customerBalance=" + customerBalance
				+ "]";
	}


	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String toAccountDetails() {
		return "Account [Account Number= " + customerAccountNumber + ","
				+ " Type= " + customerAccountType + ", "
				+ " Ownership= " + customerOwnership +", "
				+"  Branch=" + customerBranch + ","
				+ " Ifsc Code=" + customerIfscCode
				+ ", Current Balance=" + customerBalance + 
				"]";
	} 


}
