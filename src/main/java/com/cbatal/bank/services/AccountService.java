package com.cbatal.bank.services;

import com.cbatal.bank.entities.Account;
import com.cbatal.bank.exceptions.FunctionalException;

public interface AccountService {
	
	Account deposite(String accountNum, String  clientId, double amount) throws FunctionalException;
	Account withdraw(String accountNum, String clientId, double amount) throws FunctionalException;
	Account transfer(String fromAccountNum, String clientId, double amount, String toAccountNum) throws FunctionalException;
}
