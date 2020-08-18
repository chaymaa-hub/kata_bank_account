package com.cbatal.bank.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.cbatal.bank.entities.Account;
import com.cbatal.bank.entities.Client;
import com.cbatal.bank.exceptions.custom.AccountNotFoundException;
import com.cbatal.bank.exceptions.custom.InsufficientBalanceException;
import com.cbatal.bank.exceptions.custom.NegativeAmountException;
import com.cbatal.bank.repositories.FakeDatabase;
import com.cbatal.bank.services.AccountService;

@SpringBootTest
public class AccountServiceImplTest {
	
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
	void depoisteNegativeAmountTest() {
		Assertions.assertThrows(NegativeAmountException.class, () -> {
			accountService.deposite("account_2", "client_2", -50);
		});
	}
	
	@Test
	void depoisteAccountNumNotFoundTest() {
		Assertions.assertThrows(AccountNotFoundException.class, () -> {
			accountService.deposite("account_3", "client_1", 30);
		});
	}
	
	@Test
	void depoisteAccountDoesntBelongToTheClientTest() {
		Assertions.assertThrows(AccountNotFoundException.class, () -> {
			accountService.deposite("account_2", "client_1", 30);
		});
	}
	
	@Test
	void depoisteSuccessTest() throws Exception {
		Account account = accountService.deposite("account_2", "client_2", 70);
		Assertions.assertNotNull(account);
		Assertions.assertEquals(370, account.getBalance());
	}
	
	@Test
	void withdrawNegativeAmountTest() {
		Assertions.assertThrows(NegativeAmountException.class, () -> {
			accountService.withdraw("account_2", "client_2", -50);
		});
	}
	
	@Test
	void withdrawAccountNumNotFoundTest() {
		Assertions.assertThrows(AccountNotFoundException.class, () -> {
			accountService.withdraw("account_3", "client_1", 30);
		});
	}
	
	@Test
	void withdrawAccountDoesntBelongToTheClientTest() {
		Assertions.assertThrows(AccountNotFoundException.class, () -> {
			accountService.withdraw("account_2", "client_1", 30);
		});
	}
	
	@Test
	void withdrawInsufficientBalanceTest() {
		Assertions.assertThrows(InsufficientBalanceException.class, () -> {
			accountService.withdraw("account_2", "client_2", 600);
		});
	}
	
	@Test
	void withdrawSuccessTest() throws Exception {
		Account account = accountService.withdraw("account_2", "client_2", 70);
		Assertions.assertNotNull(account);
		Assertions.assertEquals(230, account.getBalance());
	}
	
	
	
	
	
	@Test
	void transferNegativeAmountTest() {
		Assertions.assertThrows(NegativeAmountException.class, () -> {
			accountService.transfer("account_2", "client_2", -50, "account_1");
		});
	}
	
	@Test
	void transferFromAccountNumNotFoundTest() {
		Assertions.assertThrows(AccountNotFoundException.class, () -> {
			accountService.transfer("account_3", "client_1", 30, "account_1");
		});
	}
	
	@Test
	void transferAccountDoesntBelongToTheClientTest() {
		Assertions.assertThrows(AccountNotFoundException.class, () -> {
			accountService.transfer("account_2", "client_1", 30, "account_1");
		});
	}
	
	@Test
	void transferInsufficientBalanceTest() {
		Assertions.assertThrows(InsufficientBalanceException.class, () -> {
			accountService.transfer("account_2", "client_2", 600, "account_1");
		});
	}
	
	@Test
	void transferToAccountNumNotFoundTest() {
		Assertions.assertThrows(AccountNotFoundException.class, () -> {
			accountService.transfer("account_2", "client_2", 30, "account_3");
		});
	}
	
	@Test
	void transferSuccessTest() throws Exception {
		Account account = accountService.transfer("account_2", "client_2", 70, "account_1");
		Assertions.assertNotNull(account);
		Assertions.assertEquals(230, account.getBalance());
	}
	
}
