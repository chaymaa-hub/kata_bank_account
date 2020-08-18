package com.cbatal.bank.repositories;

import java.util.List;

import com.cbatal.bank.entities.Transaction;

public interface TransactionRepository {
	
	Transaction saveTransaction(Transaction transaction);
	
	List<Transaction> getTransactions(String accountNum);
}
