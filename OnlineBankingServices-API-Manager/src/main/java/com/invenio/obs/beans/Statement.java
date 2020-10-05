package com.invenio.obs.beans;

import java.util.List;


public class Statement {

	private List<Transaction> transactionList;

	public List<Transaction> getStatement() {
		return transactionList;
	}

	public void setStatement(List<Transaction> transactionList) {
		this.transactionList = transactionList;
	}
	
	
}
