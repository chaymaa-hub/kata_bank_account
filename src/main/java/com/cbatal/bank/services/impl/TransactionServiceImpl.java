package com.cbatal.bank.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbatal.bank.entities.Transaction;
import com.cbatal.bank.repositories.TransactionRepository;
import com.cbatal.bank.services.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public List<Transaction> getTransactions(String accountNum) {
		return transactionRepository.getTransactions(accountNum);
	}
	
}
