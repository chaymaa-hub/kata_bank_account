package com.cbatal.bank.repositories.impl;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.cbatal.bank.entities.Account;
import com.cbatal.bank.repositories.AccountRepository;
import com.cbatal.bank.repositories.FakeDatabase;

@Repository
public class AccountRepositoryImpl implements AccountRepository {
	
	public Account saveAccount(Account account) {
		return FakeDatabase.saveAccount(account);
	}

	@Override
	public Optional<Account> findAccountByNumAndClient(String accountNum, String clientId) {
		return FakeDatabase.findAccountByNumAndClient(accountNum, clientId);
	}

	@Override
	public Optional<Account> findAccountByNum(String accountNum) {
		return FakeDatabase.findAccountByNum(accountNum);
	}
	
}
