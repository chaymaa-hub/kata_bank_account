package com.cbatal.bank.services.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cbatal.bank.entities.Account;
import com.cbatal.bank.entities.Client;
import com.cbatal.bank.entities.Transaction;
import com.cbatal.bank.exceptions.FunctionalException;
import com.cbatal.bank.repositories.FakeDatabase;
import com.cbatal.bank.services.AccountService;
import com.cbatal.bank.services.TransactionService;

@SpringBootTest
public class TransactionServiceImplTest {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;
	
	@BeforeEach
	void init() {
		Map<String, Account> accounts  = new HashMap<>();
		Map<String, Client> clients  = new HashMap<>();
		clients.put("client_1", new Client("client_1", "Client 1"));
		clients.put("client_2", new Client("client_2", "Client 2"));
		accounts.put("account_1", new Account("account_1", "client_1", 0));
		accounts.put("account_2", new Account("account_2", "client_2", 300));
		FakeDatabase.init(accounts, clients);
	}

	@Test
	public void getDepositeTransactionsTest() throws FunctionalException {
		List<Transaction> transactions = transactionService.getTransactions("account_1");
		Assertions.assertTrue(transactions.isEmpty());
		accountService.deposite("account_1", "client_1", 40);
		accountService.deposite("account_1", "client_1", 60);
		transactions = transactionService.getTransactions("account_1");
		Assertions.assertEquals(2, transactions.size());
	}
	
	@Test
	public void getWithdrawTransactionsTest() throws FunctionalException {
		List<Transaction> transactions = transactionService.getTransactions("account_2");
		Assertions.assertTrue(transactions.isEmpty());
		accountService.withdraw("account_2", "client_2", 40);
		accountService.withdraw("account_2", "client_2", 60);
		transactions = transactionService.getTransactions("account_2");
		Assertions.assertEquals(2, transactions.size());
	}
	
	@Test
	public void getTransferTransactionsTest() throws FunctionalException {
		List<Transaction> transactionsAccount2 = transactionService.getTransactions("account_2");
		List<Transaction> transactionsAccount1 = transactionService.getTransactions("account_1");
		Assertions.assertTrue(transactionsAccount2.isEmpty());
		Assertions.assertTrue(transactionsAccount1.isEmpty());
		accountService.transfer("account_2", "client_2", 40, "account_1");
		transactionsAccount2 = transactionService.getTransactions("account_2");
		transactionsAccount1 = transactionService.getTransactions("account_1");
		Assertions.assertEquals(1, transactionsAccount2.size());
		Assertions.assertEquals(1, transactionsAccount1.size());
	}
	
}
