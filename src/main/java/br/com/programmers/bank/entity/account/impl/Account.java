package br.com.programmers.bank.entity.account.impl;

import static br.com.programmers.bank.commons.utils.AssertUtils.assertLongMinimumValue;
import static br.com.programmers.bank.commons.utils.AssertUtils.notBlank;
import static br.com.programmers.bank.commons.utils.AssertUtils.notNull;

import br.com.programmers.bank.entity.account.ICreditableAccount;
import br.com.programmers.bank.entity.account.IDebitableAccount;
import br.com.programmers.bank.entity.account.balance.Balance;

public class Account implements ICreditableAccount, IDebitableAccount {
	
	private static final String ACCOUNT_BALANCE_REQUIRED = "An account balance is required for accounts";
	private static final long MINIMUM_ALLOWED_ACCOUNT_ID = 1L;
	private static final String INVALID_ACCOUNT_ID = "The account id must be equal or greater than " + MINIMUM_ALLOWED_ACCOUNT_ID;
	private static final String AGENCY_REQUIRED = "An account cannot be initialized without its agency identifier";
	
	private final int accountId;
	private final String agency;
	private final Balance accountBalance;
	
	public Account(Balance accountBalance, int accountId, String agency) {
		notNull(accountBalance, ACCOUNT_BALANCE_REQUIRED);
		assertLongMinimumValue(accountId, MINIMUM_ALLOWED_ACCOUNT_ID, INVALID_ACCOUNT_ID);
		notBlank(agency, AGENCY_REQUIRED);
		
		this.accountBalance = accountBalance.copy();
		this.accountId = accountId;
		this.agency = agency;
	}
	
	@Override
	public void credit(double creditAmount) {
		this.accountBalance.credit(creditAmount);
	}

	@Override
	public void debit(double debitAmount) {
		this.accountBalance.debit(debitAmount);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountBalance == null) ? 0 : accountBalance.hashCode());
		result = prime * result + accountId;
		result = prime * result + ((agency == null) ? 0 : agency.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountBalance == null) {
			if (other.accountBalance != null)
				return false;
		} else if (!accountBalance.equals(other.accountBalance))
			return false;
		if (accountId != other.accountId)
			return false;
		if (agency == null) {
			if (other.agency != null)
				return false;
		} else if (!agency.equals(other.agency))
			return false;
		return true;
	}

	public Account copy() {
		return new Account(this.accountBalance.copy(), accountId, agency);
	}

	public int getAccountId() {
		return accountId;
	}

	public Balance getAccountBalance() {
		return accountBalance;
	}
	
	public String getAgency() {
		return agency;
	}
}

