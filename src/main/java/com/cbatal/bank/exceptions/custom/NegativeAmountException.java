package com.cbatal.bank.exceptions.custom;

import com.cbatal.bank.exceptions.FunctionalException;

public class NegativeAmountException extends FunctionalException {
	
	private static final long serialVersionUID = -1632853023957666961L;
	
	double amount;

	public NegativeAmountException(double amount) {
		super("Negative amount is not accepted (givent amount : ".concat(Double.toString(amount)).concat(")"));
		this.amount = amount;
	}
	
	
}
