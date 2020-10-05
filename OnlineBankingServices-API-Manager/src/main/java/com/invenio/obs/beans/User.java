package com.invenio.obs.beans;

public class User {
	
	//class attributes
	
	private int userId;
	private String firstName;
	private String middleName;
	private String lastName;
	private String userName;
	private String userPhoneNumber;
	private String userEmail;
	private String userPassword;
	private String userDOB;
	private String userAddress;
	
	//toString 
	
	@Override
	public String toString() {
		return "User userId=" + userId + ", firstName=" + firstName
				+ " middleName=" + middleName + "\n lastName=" + lastName
				+ " userName=" + userName + "\n userPhoneNumber="
				+ userPhoneNumber + ", userEmail=" + userEmail
				+ "\n userDOB=" + userDOB
				+ " userAddress=" + userAddress ;
	}

	//getter and setter
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPhoneNumber() {
		return userPhoneNumber;
	}

	public void setUserPhoneNumber(String userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getUserDOB() {
		return userDOB;
	}

	public void setUserDOB(String userDOB) {
		this.userDOB = userDOB;
	}

	public String getUserAddress() {
		return userAddress;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}
	
	//constructor using superclass
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	//constructor using field
	public User(int userId, String firstName, String middleName,
			String lastName, String userName, String userPhoneNumber,
			String userEmail,  String userDOB,
			String userAddress) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userName = userName;
		this.userPhoneNumber = userPhoneNumber;
		this.userEmail = userEmail;
		this.userDOB = userDOB;
		this.userAddress = userAddress;
	}
	

	public User(int userId, String firstName, String middleName,
			String lastName, String userName, String userPhoneNumber,
			String userEmail, String userPassword, String userDOB,
			String userAddress) {
		super();
		this.userId = userId;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.userName = userName;
		this.userPhoneNumber = userPhoneNumber;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userDOB = userDOB;
		this.userAddress = userAddress;
	}
	
	
}
