package br.com.programmers.bank.model.transfer.money;

import static br.com.programmers.bank.commons.utils.AssertUtils.notEquals;
import static br.com.programmers.bank.commons.utils.AssertUtils.notNull;

import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.repository.IAccountRepository;

public class MoneyTransferParameters {
	
	private static final String NULL_ACCOUNT_REPOSITORY = "The account respository cannot be null for the transfer executor process";
	private static final String NULL_ORIGIN_ACCOUNT = "The origin account cannot be null for the transfer executor process";
	private static final String NULL_DESTINY_ACCOUNT = "The destiny account cannot be null for the transfer executor process";
	private static final String EQUAL_ORIGIN_AND_DESTINY_ACCOUNT = "The origin and destiny accounts cannot be equal";
	
	private final IAccountRepository accountRepository;
	private final Account originAccount;
	private final Account destinyAccount;
	private final double amount;

	private MoneyTransferParameters(Account originAccount, Account destinyAccount, double amount, IAccountRepository accountRepository) {
		this.accountRepository = accountRepository;
		this.originAccount = originAccount;
		this.destinyAccount = destinyAccount;
		this.amount = amount;
	}
	
	public static Builder builder() {
		return new Builder();
	}
	
	public static class Builder {
		private IAccountRepository accountRepository;
		private Account originAccount;
		private Account destinyAccount;
		private double amount;
		
		public Builder accountRepository(IAccountRepository accountRepository) {
			notNull(accountRepository, NULL_ACCOUNT_REPOSITORY);
			this.accountRepository = accountRepository;
			return this;
		}
		
		public Builder originAccount(Account originAccount) {
			notNull(originAccount, NULL_ORIGIN_ACCOUNT);
			this.originAccount = originAccount;
			return this;
		}
		
		public Builder destinyAccount(Account destinyAccount) {
			notNull(destinyAccount, NULL_DESTINY_ACCOUNT);
			this.destinyAccount = destinyAccount;
			return this;
		}
		
		public Builder amount(double amount) {
			this.amount = amount;
			return this;
		}
		
		public MoneyTransferParameters build() {
			notEquals(originAccount, destinyAccount, EQUAL_ORIGIN_AND_DESTINY_ACCOUNT);
			return new MoneyTransferParameters(originAccount, destinyAccount, amount, accountRepository);
		}
	}

	public IAccountRepository getAccountRepository() {
		return accountRepository;
	}

	public Account getOriginAccount() {
		return originAccount;
	}

	public Account getDestinyAccount() {
		return destinyAccount;
	}

	public double getAmount() {
		return amount;
	}
}
