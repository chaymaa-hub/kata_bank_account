package com.cbatal.bank.exceptions.custom;

import com.cbatal.bank.exceptions.FunctionalException;

public class InsufficientBalanceException extends FunctionalException {
	
	private static final long serialVersionUID = -1632853023957666961L;
	
	double amount;

	public InsufficientBalanceException(double amount) {
		super("Operation rejected du to insufficient balance (requested amount : ".concat(Double.toString(amount)).concat(")"));
		this.amount = amount;
	}
	
	
}
