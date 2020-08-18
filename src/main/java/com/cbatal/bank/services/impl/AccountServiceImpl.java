package com.cbatal.bank.services.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cbatal.bank.entities.Account;
import com.cbatal.bank.entities.Transaction;
import com.cbatal.bank.entities.enums.TransactionType;
import com.cbatal.bank.exceptions.FunctionalException;
import com.cbatal.bank.exceptions.custom.AccountNotFoundException;
import com.cbatal.bank.exceptions.custom.InsufficientBalanceException;
import com.cbatal.bank.exceptions.custom.NegativeAmountException;
import com.cbatal.bank.repositories.AccountRepository;
import com.cbatal.bank.repositories.TransactionRepository;
import com.cbatal.bank.services.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Account deposite(String accountNum, String clientId, double amount) throws FunctionalException {
		checkAmount(amount);
		Account account = findClientAccount(accountNum, clientId);
		creditAccount(account, amount);
		Account updatedAccount = accountRepository.saveAccount(account);
		saveTransaction("Deposite", accountNum, TransactionType.DEPOSIT, amount);
		return updatedAccount;
	}
	
	@Override
	public Account withdraw(String accountNum, String clientId, double amount) throws FunctionalException {
		checkAmount(amount);
		Account account = findClientAccount(accountNum, clientId);
		checkBalance(amount, account);
		debitAccount(account, amount);
		Account updatedAccount = accountRepository.saveAccount(account);
		saveTransaction("Withdraw", accountNum, TransactionType.WITHDRAW, amount);
		return updatedAccount;
	}

	@Override
	public Account transfer(String fromAccountNum, String clientId, double amount, String toAccountNum) throws FunctionalException {
		checkAmount(amount);
		Account fromAccount = findClientAccount(fromAccountNum, clientId);
		checkBalance(amount, fromAccount);
		Account toAccount = accountRepository.findAccountByNum(toAccountNum).orElseThrow(() -> new AccountNotFoundException(toAccountNum));
		debitAccount(fromAccount, amount);
		creditAccount(toAccount, amount);
		Account updatedAccount = accountRepository.saveAccount(fromAccount);
		saveTransaction("Transfer to ".concat(toAccountNum),fromAccountNum, TransactionType.WITHDRAW, amount);
		accountRepository.saveAccount(toAccount);
		saveTransaction("Transfer from ".concat(fromAccountNum), toAccountNum, TransactionType.DEPOSIT, amount);
		return updatedAccount;
	}

	private Account findClientAccount(String fromAccountNum, String clientId) throws AccountNotFoundException {
		Account fromAccount = accountRepository.findAccountByNumAndClient(fromAccountNum, clientId).orElseThrow(() -> new AccountNotFoundException(fromAccountNum, clientId));
		return fromAccount;
	}
	
	private void saveTransaction(String label, String fromAccountNum, TransactionType type, double amount) {
		transactionRepository.saveTransaction(new Transaction("IDENTITY", label, fromAccountNum, new Date(), type, amount));
	}
	
	private void checkBalance(double amount, Account fromAccount) throws InsufficientBalanceException {
		if (fromAccount.getBalance() < amount) throw new InsufficientBalanceException(amount);
	}

	private void checkAmount(double amount) throws NegativeAmountException {
		if (amount <= 0) throw new NegativeAmountException(amount);
	}
	
	private void creditAccount(Account account, double amount) {
		account.setBalance(account.getBalance() + amount);
	}
	
	private void debitAccount(Account account, double amount) {
		account.setBalance(account.getBalance() - amount);
	}
	
}
