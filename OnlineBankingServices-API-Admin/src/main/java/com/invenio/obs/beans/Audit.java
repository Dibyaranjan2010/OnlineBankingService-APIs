package com.invenio.obs.beans;

import java.sql.Date;
import java.sql.Time;

public class Audit {
	
	private int auditId;
	private Time changeTime;
	private Date changeDate;
	private String changeField;
	private String columnPreviousValue;
	private String columnChangedValue;
	
	//toString 
	
	@Override
	public String toString() {
		return "Audit [auditId=" + auditId + ", changeTime=" + changeTime
				+ ", changeDate=" + changeDate + ", changeField=" + changeField
				+ ", columnPreviousValue=" + columnPreviousValue
				+ ", columnChangedValue=" + columnChangedValue + "]";
	}

	//getter and setter

	public int getAuditId() {
		return auditId;
	}



	public void setAuditId(int auditId) {
		this.auditId = auditId;
	}



	public Time getChangeTime() {
		return changeTime;
	}



	public void setChangeTime(Time changeTime) {
		this.changeTime = changeTime;
	}



	public Date getChangeDate() {
		return changeDate;
	}



	public void setChangeDate(Date changeDate) {
		this.changeDate = changeDate;
	}



	public String getChangeField() {
		return changeField;
	}



	public void setChangeField(String changeField) {
		this.changeField = changeField;
	}



	public String getColumnPreviousValue() {
		return columnPreviousValue;
	}



	public void setColumnPreviousValue(String columnPreviousValue) {
		this.columnPreviousValue = columnPreviousValue;
	}



	public String getColumnChangedValue() {
		return columnChangedValue;
	}



	public void setColumnChangedValue(String columnChangedValue) {
		this.columnChangedValue = columnChangedValue;
	}

	//constructor using superclass

	public Audit() {
		super();
		// TODO Auto-generated constructor stub
	}

	//constructor using field

	public Audit(int auditId, Time changeTime, Date changeDate,
			String changeField, String columnPreviousValue,
			String columnChangedValue) {
		super();
		this.auditId = auditId;
		this.changeTime = changeTime;
		this.changeDate = changeDate;
		this.changeField = changeField;
		this.columnPreviousValue = columnPreviousValue;
		this.columnChangedValue = columnChangedValue;
	}
	
}
