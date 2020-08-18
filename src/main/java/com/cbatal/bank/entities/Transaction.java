package com.cbatal.bank.entities;

import java.util.Date;

import com.cbatal.bank.entities.enums.TransactionType;

public class Transaction {
	
	private String id;
	private String label;
	private String accountNum;
	private Date date;
	private TransactionType transactionType;
	private double amount;
	
	public Transaction(String id, String label, String accountNum, Date date, TransactionType transactionType, double amount) {
		super();
		this.id = id;
		this.label = label;
		this.accountNum = accountNum;
		this.date = date;
		this.transactionType = transactionType;
		this.amount = amount;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", label=" + label + ", accountNum=" + accountNum + ", date=" + date
				+ ", transactionType=" + transactionType + ", amount=" + amount + "]";
	}
	
}
