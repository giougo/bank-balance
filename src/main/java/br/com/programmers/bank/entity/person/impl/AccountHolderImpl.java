package br.com.programmers.bank.entity.person.impl;

import static br.com.programmers.bank.commons.utils.stream.predicate.AccountPredicateUtils.hasTheAccountTheGivenAccountId;
import static java.lang.String.format;

import java.util.Set;

import br.com.programmers.bank.commons.exception.UnauthorizedMoneyTransferException;
import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.entity.person.AccountHolder;
import br.com.programmers.bank.service.IMoneyTransfer;

public class AccountHolderImpl extends AccountHolder {
	
	private static final String UNAVAILABLE_TRANSFER_MONEY_SERVICE = "The money transfer service is currently unavailable for the account holder";
	private static final String UNAUTHORIZED_MONEY_TRANSFER_ATTEMPT = "The account holder < %s > cannot transfer money from the account < %s > since he doesnt own it";
	
	private final IMoneyTransfer moneyTransfer;

	public AccountHolderImpl(Person person, Set<Account> accounts, IMoneyTransfer moneyTransfer) {
		super(person, accounts);
		this.moneyTransfer = moneyTransfer;
	}

	@Override
	public void transfer(int originAccountId, int destinyAccountId, double amount) {
		checkTransferValidity(originAccountId);
		moneyTransfer.transfer(originAccountId, destinyAccountId, amount);
	}
	
	private void checkTransferValidity(int originAccountId) {
		if (moneyTransfer == null) {
			throw new UnsupportedOperationException(UNAVAILABLE_TRANSFER_MONEY_SERVICE);
		}
		
		if(!isOriginAccountPropertyOfTheUser(originAccountId)) {
			String errorMessage = format(UNAUTHORIZED_MONEY_TRANSFER_ATTEMPT, person.getId(), originAccountId);
			throw new UnauthorizedMoneyTransferException(errorMessage);
		}
	}

	private boolean isOriginAccountPropertyOfTheUser(int originAccountId) {
		return accounts.stream().anyMatch(account -> hasTheAccountTheGivenAccountId.test(account, originAccountId));
	}

	@Override
	public AccountHolder copy() {
		return new AccountHolderImpl(this.person, accounts, moneyTransfer);
	}
}
