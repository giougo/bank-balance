package br.com.programmers.bank.service.impl;

import br.com.programmers.bank.commons.exception.MoneyTransferException;
import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.model.transfer.money.MoneyTransferParameters;
import br.com.programmers.bank.repository.IAccountRepository;
import br.com.programmers.bank.service.IMoneyTransferExecutor;

public class DefaultMoneyTransferExecutor implements IMoneyTransferExecutor {

	private static final String ERROR_DURING_MONEY_TRANSFER_PROCESS = "An error occurred during the money transfer process";

	public void executeMoneyTransfer(MoneyTransferParameters parameters) throws MoneyTransferException {
		try {
			exchangeAmountBetweenAccounts(parameters.getOriginAccount(), parameters.getDestinyAccount(), parameters.getAmount());
			persistExchangeBetweenAccounts(parameters.getOriginAccount(), parameters.getDestinyAccount(), parameters.getAccountRepository());

		} catch (Exception e) {
			throw new MoneyTransferException(ERROR_DURING_MONEY_TRANSFER_PROCESS, e);
		}
	}

	private void exchangeAmountBetweenAccounts(Account originAccount, Account destinyAccount, double amount) {
		originAccount.debit(amount);
		destinyAccount.credit(amount);
	}

	private void persistExchangeBetweenAccounts(Account originAccount, Account destinyAccount, IAccountRepository accountRepository) {
		accountRepository.persistAll(originAccount, destinyAccount);
	}
}
