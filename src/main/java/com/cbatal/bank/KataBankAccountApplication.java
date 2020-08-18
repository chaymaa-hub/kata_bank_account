package com.cbatal.bank;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cbatal.bank.entities.Account;
import com.cbatal.bank.entities.Client;
import com.cbatal.bank.exceptions.FunctionalException;
import com.cbatal.bank.repositories.FakeDatabase;
import com.cbatal.bank.services.AccountService;
import com.cbatal.bank.services.TransactionService;

@SpringBootApplication
public class KataBankAccountApplication {
	
	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private AccountService accountService;

	public static void main(String[] args) {
		SpringApplication.run(KataBankAccountApplication.class, args);
	}
	
	@PostConstruct
	private void initDatabase() throws FunctionalException {
		Client client1 = new Client("client_1", "Client 1");
		Client client2 = new Client("client_2", "Client 2");
		Account account1 = new Account("account_1", "client_1", 0);
		Account account2 = new Account("account_2", "client_2", 300);
		Map<String, Account> accounts  = new HashMap<>();
		Map<String, Client> clients  = new HashMap<>();
		clients.put("client_1", client1);
		clients.put("client_2", client2);
		accounts.put("account_1", account1);
		accounts.put("account_2", account2);
		FakeDatabase.init(accounts, clients);
		runSimulation(client1, client2, account1, account2);
	}
	
	private void runSimulation(Client client1, Client client2, Account account1, Account account2) throws FunctionalException {
		System.out.println("Initial state");
		System.out.println(account1);
		System.out.println(account2);
		System.out.println("Deposite 100 euros to account1");
		accountService.deposite("account_1", "client_1", 100);
		System.out.println(account1);
		System.out.println("Withdraw 100 euros from account2");
		accountService.withdraw("account_2", "client_2", 100);
		System.out.println(account2);
		System.out.println("Transfer 50 euros from account2 to account1");
		accountService.transfer("account_2", "client_2", 50, "account_1");
		System.out.println(account1);
		System.out.println(account2);
		System.out.println("Transactions history for account1");
		transactionService.getTransactions("account_1").forEach(System.out::println);
		System.out.println("Transactions history for account2");
		transactionService.getTransactions("account_2").forEach(System.out::println);
	}

}
