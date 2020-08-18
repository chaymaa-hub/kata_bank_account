package com.cbatal.bank.exceptions.custom;

import com.cbatal.bank.exceptions.FunctionalException;

public class AccountNotFoundException extends FunctionalException {
	
	private static final long serialVersionUID = -1632853023957666961L;
	
	String accountNum;
	String clientId;

	public AccountNotFoundException(String accountNum, String clientId) {
		super(
				new StringBuilder()
					.append("Account not found (num: ").append(accountNum).append(")")
					.append(" for clientId: ").append(clientId)
					.toString()
		);
		this.accountNum = accountNum;
		this.clientId = clientId;
	}
	
	public AccountNotFoundException(String accountNum) {
		super(
				new StringBuilder()
					.append("Account not found (num: ").append(accountNum).append(")")
					.toString()
		);
		this.accountNum = accountNum;
	}
	
	
}
