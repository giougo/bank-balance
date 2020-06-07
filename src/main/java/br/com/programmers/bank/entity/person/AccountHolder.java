package br.com.programmers.bank.entity.person;

import java.util.Collections;
import java.util.Set;

import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.entity.person.impl.Person;

import static br.com.programmers.bank.commons.utils.AssertUtils.notNull;
import static br.com.programmers.bank.commons.utils.AssertUtils.notEmpty;

public abstract class AccountHolder {
	
	private static final String NULL_PERSON_NOT_ALLOWED = "Null person is not allowed";
	private static final String EMPTY_ACCOUNTS = "An account holder must have at least one active account";
	
	protected final Person person;
	protected final Set<Account> accounts;
	
	protected AccountHolder(Person person, Set<Account> accounts) {
		notNull(person, NULL_PERSON_NOT_ALLOWED);
		notEmpty(accounts, EMPTY_ACCOUNTS);
		
		this.person = person;
		this.accounts = Collections.unmodifiableSet(accounts);
	}
	
	public abstract void transfer(int originAccountId, int destinyAccountId, double amount);
	public abstract AccountHolder copy();
}
