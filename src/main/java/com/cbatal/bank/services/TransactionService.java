package com.cbatal.bank.services;

import java.util.List;

import com.cbatal.bank.entities.Transaction;

public interface TransactionService {
	
	List<Transaction> getTransactions(String accountNum);
	
}
