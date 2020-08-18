package com.cbatal.bank.entities;

public class Account{
	
	private String accountNum;
	private String clientId;
	private double balance;
	
	public Account(String accountNum, String clientId, double balance) {
		super();
		this.accountNum = accountNum;
		this.clientId = clientId;
		this.balance = balance;
	}
	
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountNum=" + accountNum + ", clientId=" + clientId + ", balance=" + balance + "]";
	}
	
}
