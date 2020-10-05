package com.invenio.obs.daos;



import java.io.File;

import java.io.FileWriter;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


import com.invenio.obs.beans.Customer;
import com.invenio.obs.beans.Transaction;
import com.invenio.obs.interfaces.CustomerDaoInterface;


@Repository
public class CustomerDao implements CustomerDaoInterface{

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private int customerId=0;//123


	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;//59
	}
	//TODO ---------------------------------------------
	//TODO LOGIN
	@Override
	public String loginCustomer(int customerId, String customerPassword) {

		String name=null;
		try {
			String sql="SELECT customerFirstName FROM CUSTOMER WHERE customerId=? AND customerPassword=?";
			name=jdbcTemplate.queryForObject(sql, new Object[] {customerId,customerPassword}, String.class);
		}catch(Exception e) {
			return null;
		}

		setCustomerId(customerId);
		return name;
	}

	//TODO FORGET PASSWORD
    @Override
    public String forgetPassword( String customerEmailId, int customerPin) {
            String sql1="Select customerPin from customer where customerEmailId=?";
            int pin=0;
            try {
                pin=jdbcTemplate.queryForObject(sql1, new Object[] {customerEmailId}, Integer.class);
            }catch(Exception e) {
                return "INVALID";
            }
            if(pin!=customerPin ) {
                return "INVALID";
            }
            else {
                int i=0;
                String password=passwordGeneration();
                String sql2="update customer set customerPassword=? where customerEmailId=?";
                try {
                    i=jdbcTemplate.update(sql2,password,customerEmailId);
                }catch(Exception e) {
                    return "INVALID_EMAIL";
                }
              
                if(i==1) {
                    return password;
                }
                else {
                    return "ERROR";
                }

     

            }
       
    }
    
  //TODO PASSWORD GENERATION
    public String passwordGeneration() {
        final String chars="ABCDEFGHIJ&abcdefgh#&@$IJKLMNOPijklmnopQRSTUVWXYZqrstuvwxyz";
        SecureRandom random=new SecureRandom();
        StringBuilder password=new StringBuilder();
        for(int i=0;i<10;i++) {
            int index=random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }   
        return password.toString();   
    }
    
	//TODO UPDATE PASSWORD
	@Override
	public int updatePassword(String existingPassword, String customerUpdatePassword) {
		if(getCustomerId()==0) {
			return 909;
		}
		else {
			String sql1="Select customerPassword from customer where customerId=?";
			String password=jdbcTemplate.queryForObject(sql1, new Object[] {getCustomerId()}, String.class);
			if(password.equals(existingPassword)) {
				String sql2="update customer set customerPassword=? where customerId=?";
				int n=jdbcTemplate.update(sql2,customerUpdatePassword,getCustomerId());
				if(n==1) 
					return 1;
				else {
					return 702;
				}
			}
			else {
				return 703;
			}
		}
	}


	//TODO VIEW ACCOUNT BALANCE
	@Override
	public double viewAccountBalance() {
		if(getCustomerId()==0) {
			return -909;
		}
		else {
			Double balance=0.0;
			String sql="Select customerBalance from customer where customerId=?";
			try {
				balance=jdbcTemplate.queryForObject(sql, new Object[] {getCustomerId()}, Double.class);
			}catch(Exception e) {
				return 800;
			}
			return balance;
		}
	}



	//TODO VIEW ACCOUNT DETAILS
	@Override
	public Customer viewAccountDetails() {
		Customer acc=new Customer();
		Customer details=new Customer();
		if(getCustomerId()==0) {
			return null;
		}
		else {
			String sql= "Select * from customer where customerId=?";

			System.out.println(getCustomerId());
			try {
				details= jdbcTemplate.queryForObject(sql, new Object[] {getCustomerId()},new BeanPropertyRowMapper<Customer>(Customer.class));

			}catch(Exception e) {
				acc.setCustomerAccountNumber(-999);
				return acc;
			}
			return details;
		}
	}

	//TODO VIEW STATEMENT
	@Override
	public List<Transaction> viewStatement(String startDate, String endDate) {
		List<Transaction> transactionList=new ArrayList<Transaction>();
		if(getCustomerId()==0) {
			return null;
		}


		else {
			List<Map<String, Object>> statements=new ArrayList<Map<String, Object>>();	
			String sql= "Select * from transaction where transactionCustomerId=? AND transactionDate between ? AND ?";
			statements=jdbcTemplate.queryForList(sql,getCustomerId(),startDate,endDate);
			for (Map<String, Object> map: statements) {
				Transaction transaction=new Transaction();
				transaction.setTransactionId((Integer)map.get("transactionId"));
				transaction.setTransactionTime((java.sql.Time)map.get("transactionTime"));
				transaction.setTransactionDate((java.sql.Date)map.get("transactionDate"));	
				transaction.setTransactionAmount((Double)map.get("transactionAmount"));
				transaction.setTransactionAccTo((Integer)map.get("transactionAccTo"));
				transaction.setTransactionMode((String)map.get("transactionMode"));
				transaction.setTransactionType((String)map.get("transactionType"));

				transaction.setTransactionAccFrom((Integer)map.get("transactionAccFrom"));
				transactionList.add(transaction);
			}
		}
		return transactionList;
	}


	//TODO DOWNLOAD STATEMENT
	@Override
	public String downloadStatement(String startDate, String endDate) {
		if(getCustomerId()==0) {
			return "NO_LOGIN";
		}
		List<Transaction> downloadingTransactionList=viewStatement(startDate, endDate);
		String custId=Integer.toString(getCustomerId());
		//		String currDate=java.time.LocalDate.now().toString();
		//		String currTime=java.time.LocalTime.now().toString();
		//	System.out.println(currTime);
		String fileName="E:\\MDMN_BANK_TRANSACTION_DIRECTORY\\"+custId+"_"+startDate+"_"+ endDate+".csv";
		File f=new File(fileName);
		FileWriter fw = null;
		if(f.exists()){
			f.delete();
		}
		try {
			f.createNewFile();
			fw=new FileWriter(fileName);
			fw.append("Your Transaction details between "+startDate +"and " + endDate+":");
			fw.append("\n");
			fw.append("Transaction Id");
			fw.append(',');
			fw.append("Transaction Time");
			fw.append(',');
			fw.append("Transaction Date");
			fw.append(',');
			fw.append("Transaction Amount");
			fw.append(',');
			fw.append("Transaction Mode");
			fw.append(',');
			fw.append("Transaction Type");
			fw.append(',');
			fw.append("Transaction To");
			fw.append(',');
			fw.append("Transaction From");
			fw.append(',');
			fw.append('\n');

			for(Transaction t:downloadingTransactionList) {
				String transactionId=Integer.toString(t.getTransactionId());
				//System.out.println(t.getTransactionDate().toString());
				String transactionAmount=Double.toString(t.getTransactionAmount());
				String transactionTo=Integer.toString(t.getTransactionAccTo());
				String transactionFrom=Integer.toString(t.getTransactionAccFrom());
				fw.append(transactionId);
				fw.append(',');
				fw.append((t.getTransactionTime()).toString());
				fw.append(',');
				fw.append((t.getTransactionDate()).toString());	
				fw.append(',');
				fw.append(transactionAmount);
				fw.append(',');
				fw.append(t.getTransactionMode());
				fw.append(',');
				fw.append(t.getTransactionType());
				fw.append(',');
				fw.append(transactionTo);
				fw.append(',');
				fw.append(transactionFrom);
				fw.append("\n");

			}
			f.setWritable(false);
			f.setReadOnly();
			return fileName;
		} catch (IOException e) {

			e.printStackTrace();
			return "No Data";
		}
		finally{
			try {
				fw.flush();
				fw.close();

			} catch (IOException e) {

				e.printStackTrace();
			}	
		}

	}




	//TODO LOGOUT CUSTOMER
	@Override
	public boolean logoutCustomer() {

		setCustomerId(0);
		if(getCustomerId()==0) {
			return true;
		}
		else {
			return false;
		}
	}

	//TODO WITHDRAW MONEY
	@Override
	public String withdrawMoney(double amount) {
		if(getCustomerId()==0) {
			return "No_LOGIN";
		}
		else {
			Double curBal=0.0;
			String sql1="SELECT customerBalance FROM customer WHERE customerId=?";
			try {
				curBal=jdbcTemplate.queryForObject(sql1, new Object[] {getCustomerId()},Double.class);
			}catch(Exception e) {
				System.out.println("WARNING: ERROR IN CUSTOMERDAO.withdrawMoney().1stQuery");
				return "ERROR_FETCH";
			}
			//System.out.println(curBal);
			if(curBal<amount) {
				return "NOT_ENOUGH_MONEY";
			}
			else {
				String sql2="update customer set customerBalance=customerBalance-? where customerId=?";
				int check1=0;
				try {
					check1=jdbcTemplate.update(sql2,amount,getCustomerId());
				}catch(Exception e) {
					System.out.println("WARNING: ERROR IN CUSTOMERDAO.withdrawMoney().2ndQuery");
					return  "ERROR_FETCH";
				}
				if(check1==1) {
					String sql3="INSERT INTO TRANSACTION (transactionTime, transactionDate, transactionAmount, transactionMode, transactionType, transactionCustomerId, transactionAccTo, transactionAccFrom) VALUES (?,?,?,?,?,?,?,?)";
					int check2=0;
					try {
						check2=jdbcTemplate.update(sql3,new java.sql.Timestamp(new java.util.Date().getTime()), new java.sql.Timestamp(new java.util.Date().getTime()),amount,"SELF","WITHDRAW",getCustomerId(),getAccountNumber(),getAccountNumber());
					}catch(Exception e) {
						System.out.println("MONEY_DEBITED_NOT_INSERTED_DETAILS//->"+(amount)+":"+getCustomerId());
						return "SUCCESSFUL";
					}
					if(check2==1) {
						return "SUCCESSFUL";
					}

				}
				else {
					return "TRY_LATER";
				}
				return "TRY_LATER";
			}
		}

	}

	//TODO DEPOSIT MONEY
	@Override
	public String depositMoney(double amount) {
		if(getCustomerId()==0) {
			return "No_LOGIN";
		}
		else if(amount>=100000.0) {
			return "EXCESS_AMOUNT";
		}

		else {
			int check1=0;
			String sql1="update customer set customerBalance=customerBalance+?  where customerId=?";
			try {
				check1=jdbcTemplate.update(sql1,amount,getCustomerId());
			}catch(Exception e) {
				return "ERROR_FETCH";
			}
			if(check1==1) {
				String sql2="INSERT INTO TRANSACTION (transactionTime, transactionDate, transactionAmount, transactionMode, transactionType, transactionCustomerId, transactionAccTo, transactionAccFrom) VALUES (?,?,?,?,?,?,?,?)";
				int check2=0;
				try {
					check2=jdbcTemplate.update(sql2,new java.sql.Timestamp(new java.util.Date().getTime()), new java.sql.Timestamp(new java.util.Date().getTime()),amount,"SELF","DEPOSIT",getCustomerId(),getAccountNumber(),getAccountNumber());
				}catch(Exception e) {
					System.out.println("MONEY_DEPOSITED_NOT_INSERTED_DETAILS//->"+(amount)+":"+getCustomerId());
					return "SUCCESSFUL";
				}
				if(check2==1) {
					return "SUCCESSFUL";

				}
				else {
					return "TRY_LATER";
				}
			}
		}
		return "TRY_LATER";
	}



	//TODO GET ACCOUNT NUMBER BY ID
	public int getAccountNumber() {
		String sql="SELECT customerAccountNumber FROM customer WHERE customerId=?";
		int accountNumber=jdbcTemplate.queryForObject(sql, new Object[] {getCustomerId()}, Integer.class);
		return accountNumber;
	}

	//TODO ACCOUNT NUMBER EXIST OR NOT
	public List<Integer> checkAccountNumber(int accountNumber) {
		List<Integer> list=new ArrayList<Integer>();
		String sql="SELECT customerId from CUSTOMER where customerAccountNumber=?";
		int check=jdbcTemplate.queryForObject(sql,new Object[] {accountNumber},Integer.class );
		System.out.println("*******************"+check);
		if(check>0) {
			list.add(check);
			list.add(1);
			System.out.println("LIST IN CHECK AMOUNT NUMBER"+list);
			return list;
		}
		else {
			return null;
		}
	}


	//TODO TRANSACTION MONEY
	@Override
	public String transactMoney(int recAccountNumber, double amount) {
		List<Integer> list=new ArrayList<Integer>();
		list=checkAccountNumber(recAccountNumber);

		if(getCustomerId()==0) {
			return "No_LOGIN";
		}
		else if(amount>=100000) {
			return "EXCESS_AMOUNT";
		}
		else if(list.get(0)==getAccountNumber() || list.get(0)==0) {
			return "INVALID";
		}
		else if(viewAccountBalance()<amount) {
			return "NOT_ENOUGH_MONEY";
		}
		else {
			System.out.println("ENTERS THE TRANSACTION ELSE");
			//withdraw
			String sql1="update customer set customerBalance=customerBalance-? where customerId=?";
			int check1=0;
			try {
				check1=jdbcTemplate.update(sql1,amount,getCustomerId());
			}catch(Exception e) {
				//TODO LOG INFO
				System.out.println("WARNING: ERROR IN CUSTOMERDAO.transactionMoney().2ndQuery");
				return  "ERROR700";     //Can't proceed the transaction, money not debited
			}
			//insert		
			if(check1==1) {//1
				String sql2="INSERT INTO TRANSACTION (transactionTime, transactionDate, transactionAmount, transactionMode, transactionType, transactionCustomerId, transactionAccTo, transactionAccFrom) VALUES (?,?,?,?,?,?,?,?)";
				int check2=0;
				try {
					check2=jdbcTemplate.update(sql2,new java.sql.Timestamp(new java.util.Date().getTime()), new java.sql.Timestamp(new java.util.Date().getTime()),amount,"TRANSFER","DEBITED",getCustomerId(),recAccountNumber,getAccountNumber());
				}catch(Exception e) {
					//System.out.println("MONEY_DEBITED_NOT_INSERTED_DETAILS//->"+(amount)+":"+getCustomerId());
					boolean statusRollback=refundMoney(getCustomerId(),amount,"refund");
					if(statusRollback) {//2
						return "ERROR701";// CAN'T COMPLETE THE TRANSACTION\nTHE MONEY IS REFUNDED TO YOUR ACCOUNT";
					}
					else {//-2
						//TODO LOG INFO
						System.out.println("\n\n\nERROR: IN REFUNDING MONEY//DETAILS->"+getCustomerId()+":"+amount);
						return "ERROR702";//CAN'T COMPLETE THE TRANSACTION\nMONEY GOT DEBITED FROM YOUR ACCOUNT\nIT WILL BE REFUNDED WITHIN 1 WORKING DAY\n\n*****SORRY FOR THE INCONVIENCE***";
					}
				}
				//deposit		
				if(check2==1) {//3
					int check3=0;
					String sql3="update customer set customerBalance=customerBalance+? where customerId=?";

					try {
						check3=jdbcTemplate.update(sql3,amount,list.get(0));
					}catch(Exception e) {
						boolean statusRollback=refundMoney(getCustomerId(),amount,"refund");
						if(statusRollback) {//4
							return "ERROR703";//ERROR OCCURED IN RECIPIENT BANK\nTHE MONEY IS REFUNDED TO YOUR ACCOUNT";
						}
						else {//-4
							//TODO LOG INFO
							System.out.println("\n\n\nERROR: IN REFUNDING MONEY//DETAILS->"+getCustomerId()+":"+amount);
							return "ERROR702";//CAN'T COMPLETE THE TRANSACTION\nMONEY GOT DEBITED FROM YOUR ACCOUNT\nIT WILL BE REFUNDED WITHIN 1 WORKING DAY\n\n*****SORRY FOR THE INCONVIENCE***";
						}
					}
					//insert		
					if(check3==1) {//5
						int check4=0;
						String sql4="INSERT INTO TRANSACTION (transactionTime, transactionDate, transactionAmount, transactionMode, transactionType, transactionCustomerId, transactionAccTo, transactionAccFrom) VALUES (?,?,?,?,?,?,?,?)";
						try {
							check4=jdbcTemplate.update(sql4,new java.sql.Timestamp(new java.util.Date().getTime()), new java.sql.Timestamp(new java.util.Date().getTime()),amount,"TRANSFER","CREDITED",list.get(0),recAccountNumber,getAccountNumber());
						}catch(Exception e) {
							//TODO LOG INFO
							System.out.println("\n\n\nERROR:MONEY_CREDITED_NOT_INSERTED_DETAILS//->"+(amount)+":"+list.get(0));
							return "SUCCESSFUL";
						}

						if(check4==1) {
							return "SUCCESSFUL";//: MONEY TRANSFERED TO ACCOUNT"+list.get(0);
						}
					}
				}
			}
		}
		return null;

	}

	//TODO ROLLBACK TRANSACTION
	private boolean refundMoney(int customerId2, double amount, String string) {
		String sql1="update customer set customerBalance=customerBalance+? where customerId=?";
		int check=0;
		try {
			check=jdbcTemplate.update(sql1,amount,getCustomerId());
		}
		catch(Exception e) {
			System.out.println("WARNING: ERROR IN CUSTOMERDAO.rollbackTransaction().1stQuery");
			return false;
		}
		if(check==1) {
			String sql2="INSERT INTO TRANSACTION (transactionTime, transactionDate, transactionAmount, transactionMode, transactionType, transactionCustomerId, transactionAccTo, transactionAccFrom) VALUES (?,?,?,?,?,?,?,?)";
			int check2=0;
			try {
				check2=jdbcTemplate.update(sql2,new java.sql.Timestamp(new java.util.Date().getTime()), new java.sql.Timestamp(new java.util.Date().getTime()),amount,"REFUND","CREDITED",getCustomerId(),getAccountNumber(),getAccountNumber());
			}catch(Exception e) {
				//LOG INFO
				System.out.println("\n\n\nMONEY_REFUNDED_NOT_INSERTED_DETAILS//->"+(amount)+":"+getCustomerId());
				return true;
			}
			if(check2==1) {
				return true;
			}
		}
		return false;
	}

	//TODO EXTRACTING CUSTOMER TRANSACTION DETAILS BY MANAGER
	@Override
	public List<Transaction> viewCustomerStatement(int customerAccountNumber, String startDate, String endDate) {
		List<Integer> list=checkAccountNumber(customerAccountNumber);
		int custId=list.get(0);
		if(custId==0) {
			return null;
		}
		else {
			List<Transaction> transactionList=new ArrayList<Transaction>();
			List<Map<String, Object>> statements=new ArrayList<Map<String, Object>>();	
			String sql= "Select * from transaction where transactionCustomerId=? AND transactionDate between ? AND ?";
			statements=jdbcTemplate.queryForList(sql,custId,startDate,endDate);
			for (Map<String, Object> map: statements) {
				Transaction transaction=new Transaction();
				transaction.setTransactionId((Integer)map.get("transactionId"));
				transaction.setTransactionTime((java.sql.Time)map.get("transactionTime"));
				transaction.setTransactionDate((java.sql.Date)map.get("transactionDate"));	
				transaction.setTransactionAmount((Double)map.get("transactionAmount"));
				transaction.setTransactionAccTo((Integer)map.get("transactionAccTo"));
				transaction.setTransactionMode((String)map.get("transactionMode"));
				transaction.setTransactionType((String)map.get("transactionType"));
				transaction.setTransactionAccFrom((Integer)map.get("transactionAccFrom"));
				transactionList.add(transaction);
			}
			System.out.println("*************** ::: "+transactionList.size());
			return transactionList;
		}
	}
}