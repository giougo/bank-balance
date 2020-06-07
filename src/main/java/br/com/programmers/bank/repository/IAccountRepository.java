package br.com.programmers.bank.repository;

import java.util.List;

import br.com.programmers.bank.entity.account.impl.Account;

public interface IAccountRepository {
	Account getAccountById(int id);
	List<Account> persistAll(Account... accounts);
}