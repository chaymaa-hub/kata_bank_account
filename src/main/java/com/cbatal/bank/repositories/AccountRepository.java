package com.cbatal.bank.repositories;

import java.util.Optional;

import com.cbatal.bank.entities.Account;

public interface AccountRepository {
	
	Account saveAccount(Account account);
	Optional<Account> findAccountByNumAndClient(String accountNum, String clientId);
	Optional<Account> findAccountByNum(String accountNum);
	
}
