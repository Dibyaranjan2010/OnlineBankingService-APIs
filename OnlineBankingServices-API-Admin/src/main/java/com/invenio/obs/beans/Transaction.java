package com.invenio.obs.beans;

import java.sql.Time;
import java.sql.Date;
public class Transaction {

	//class attributes
	
	private int  transactionId;
	private Time transactionTime;
	private Date transactionDate;
	private double transactionAmount;
	private String transactionMode;
	private String transactionType;
	private int transactionAccTo;
	private int transactionAccFrom;
	//constructor using field

		public Transaction(int transactionId, Time transactionTime,
				Date transactionDate, double transactionAmount,
				String transactionMode, String transactionType,int transactionAccTo,int transactionAccFrom ) {
			super();
			this.transactionId = transactionId;
			this.transactionTime = transactionTime;
			this.transactionDate = transactionDate;
			this.transactionAmount = transactionAmount;
			this.transactionMode = transactionMode;
			this.transactionType = transactionType;
			this.transactionAccTo=transactionAccTo;
			this.transactionAccFrom=transactionAccFrom;
		}
		public Transaction( Time transactionTime,
				Date transactionDate, double transactionAmount,
				String transactionMode, String transactionType,int transactionAccTo,int transactionAccFrom ) {
			super();
			this.transactionTime=transactionTime;
			this.transactionDate = transactionDate;
			this.transactionAmount = transactionAmount;
			this.transactionMode = transactionMode;
			this.transactionType = transactionType;
			this.transactionAccTo=transactionAccTo;
			this.transactionAccFrom=transactionAccFrom;
		}
	
	//toString 
	
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId
				+ ", transactionTime=" + transactionTime + ", transactionDate="
				+ transactionDate + ", transactionAmount=" + transactionAmount
				+ ", transactionMode=" + transactionMode + ", transactionType="
				+ transactionType + ", transactionAccTo="
						+ transactionAccTo +",transactionAccFrom="+transactionAccFrom +"]";
	}

	//getter and setter

	public int getTransactionId() {
		return transactionId;
	}



	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}



	public Time getTransactionTime() {
		return transactionTime;
	}



	public void setTransactionTime(Time transactionTime) {
		this.transactionTime = transactionTime;
	}



	public Date getTransactionDate() {
		return transactionDate;
	}



	public void setTransactionDate(Date date) {
		this.transactionDate = date;
	}



	public double getTransactionAmount() {
		return transactionAmount;
	}



	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}



	public String getTransactionMode() {
		return transactionMode;
	}



	public void setTransactionMode(String transactionMode) {
		this.transactionMode = transactionMode;
	}



	public String getTransactionType() {
		return transactionType;
	}



	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public int getTransactionAccTo() {
		return transactionAccTo;
	}

	public void setTransactionAccTo(int transactionAccTo) {
		this.transactionAccTo = transactionAccTo;
	}
	
	public int getTransactionAccFrom() {
		return transactionAccFrom;
	}
	public void setTransactionAccFrom(int transactionAccFrom) {
		this.transactionAccFrom = transactionAccFrom;
	}
	//constructor using superclass
	public Transaction() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	
}
