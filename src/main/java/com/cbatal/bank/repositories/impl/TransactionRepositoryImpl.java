package com.cbatal.bank.repositories.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cbatal.bank.entities.Transaction;
import com.cbatal.bank.repositories.FakeDatabase;
import com.cbatal.bank.repositories.TransactionRepository;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository {

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return FakeDatabase.saveTransaction(transaction);
	}

	@Override
	public List<Transaction> getTransactions(String accountNum) {
		return FakeDatabase.getTransactions(accountNum);
	}
	
}
