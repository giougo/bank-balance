package br.com.programmers.bank.service;

import br.com.programmers.bank.commons.exception.MoneyTransferException;
import br.com.programmers.bank.model.transfer.money.MoneyTransferParameters;

@FunctionalInterface
public interface IMoneyTransferExecutor {
	void executeMoneyTransfer(MoneyTransferParameters parameters) throws MoneyTransferException;
}
