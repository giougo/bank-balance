package br.com.programmers.bank.service.impl;

import static br.com.programmers.bank.commons.utils.AssertUtils.notNull;
import static br.com.programmers.bank.commons.utils.ExceptionSupplier.accountNotFoundById;

import java.util.Optional;

import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.model.transfer.money.MoneyTransferParameters;
import br.com.programmers.bank.repository.IAccountRepository;
import br.com.programmers.bank.service.IMoneyTransfer;

public class DefaultMoneyTransfer implements IMoneyTransfer {
	
	private static final String ACCOUNT_REPOSITORY_REQUIRED = "An account repository is required for a money transfer";
	private static final String MONEY_TRANSFER_EXECUTOR_REQUIRED = "A money transfer executor is required for a money transfer";
	
	private IAccountRepository accountRepository;
	private DefaultMoneyTransferExecutor moneyTransferExecutor;

	public DefaultMoneyTransfer(IAccountRepository accountRepository, DefaultMoneyTransferExecutor moneyTransferExecutor) {
		notNull(accountRepository, ACCOUNT_REPOSITORY_REQUIRED);
		notNull(moneyTransferExecutor, MONEY_TRANSFER_EXECUTOR_REQUIRED);
		
		this.accountRepository = accountRepository;
		this.moneyTransferExecutor = moneyTransferExecutor;
	}

	@Override
	public boolean transfer(int originAccountId, int destinyAccountId, double amount) {
		
		Account originAccount = getAccountById(originAccountId)
				.orElseThrow(accountNotFoundById(originAccountId));
		
		Account destinyAccount = getAccountById(destinyAccountId)
				.orElseThrow(accountNotFoundById(destinyAccountId));

		MoneyTransferParameters transferParameters = MoneyTransferParameters.builder()
				.accountRepository(accountRepository)
				.amount(amount)
				.originAccount(originAccount.copy())
				.destinyAccount(destinyAccount.copy())
				.build();
		
		return executeMoneyTransfer(transferParameters);
	}
	
	private Optional<Account> getAccountById(int accountId) {
		Account account = accountRepository.getAccountById(accountId);
		return Optional.ofNullable(account);
	}
	
	private boolean executeMoneyTransfer(MoneyTransferParameters parameters) {
		try {
			moneyTransferExecutor.executeMoneyTransfer(parameters);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
}
