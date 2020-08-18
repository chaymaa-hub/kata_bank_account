package com.cbatal.bank.repositories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.cbatal.bank.entities.Account;
import com.cbatal.bank.entities.Client;
import com.cbatal.bank.entities.Transaction;

public class FakeDatabase {
	
	private static final Map<String, Account> ACCOUNTS;
	private static final Map<String, Client> CLIENTS;
	private static final Map<String, List<Transaction>> TRANSACTIONS;
	
	static {
		ACCOUNTS = new HashMap<>();
		CLIENTS = new HashMap<>();
		TRANSACTIONS = new HashMap<>();
	}
	
	public static void init(Map<String, Account> accounts, Map<String, Client> clients) {
		ACCOUNTS.clear();
		CLIENTS.clear();
		TRANSACTIONS.clear();
		
		ACCOUNTS.putAll(accounts);
		CLIENTS.putAll(clients);
	}
	
	public static Optional<Account> findAccountByNum(String accountNum) {
		if (ACCOUNTS.containsKey(accountNum)) {
			return  Optional.of(ACCOUNTS.get(accountNum));
		} else {
			return Optional.empty();
		}
	}
	
	public static Optional<Account> findAccountByNumAndClient(String accountNum, String clientId) {
		if (ACCOUNTS.containsKey(accountNum)) {
			Account account = ACCOUNTS.get(accountNum);
			return account.getClientId().equals(clientId) ? Optional.of(account) : Optional.empty();
		} else {
			return Optional.empty();
		}
	}
	
	public static Account saveAccount(Account account) {
		ACCOUNTS.put(account.getAccountNum(), account);
		return account;
	}
	
	public static Transaction saveTransaction(Transaction transaction) {
		if (!TRANSACTIONS.containsKey(transaction.getAccountNum())) {
			TRANSACTIONS.put(transaction.getAccountNum(), new ArrayList<Transaction>());
		}
		TRANSACTIONS.get(transaction.getAccountNum()).add(transaction);
		return transaction;
	}
	
	public static List<Transaction> getTransactions(String accountNum) {
		return TRANSACTIONS.containsKey(accountNum) ? TRANSACTIONS.get(accountNum) : Collections.emptyList();
	}
	
}
