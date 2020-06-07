package br.com.programmers.bank.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import br.com.programmers.bank.commons.exception.MoneyTransferException;
import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.mock.MockFactory;
import br.com.programmers.bank.model.transfer.money.MoneyTransferParameters;

@RunWith(BlockJUnit4ClassRunner.class)
public class DefaultMoneyTransferExecutorTest {

	private DefaultMoneyTransferExecutor service;
	
	private static MockFactory mockFactory = MockFactory.getMockFactory();
	
	public void setup() {
		service = new DefaultMoneyTransferExecutor();
	}
	
	@Test
	public void testMustCorrectlyExchangeAndPersistTheUpdatesWhenAllParametersAreCorrect() throws MoneyTransferException {
		setup();
		
		double transferAmount = 30;
		
		MoneyTransferParameters parametersMock = mockFactory.getMoneyTransferParametersMock(transferAmount, true);
		
		Account originAccount = parametersMock.getOriginAccount();
		Account destinyAccount = parametersMock.getDestinyAccount();
		
		when(parametersMock.getAccountRepository().persistAll(originAccount, destinyAccount))
			.thenReturn(List.of(originAccount, destinyAccount));
		
		service.executeMoneyTransfer(parametersMock);
		
		assertThat(originAccount.getAccountBalance().getAmount())
			.isEqualTo(0);
			
		assertThat(destinyAccount.getAccountBalance().getAmount())
			.isEqualTo(transferAmount * 2);
	}
	
	@Test(expected = MoneyTransferException.class)
	public void testAnyExceptionsOccurredDuringTheExecutionMustBeCaughtAndThrowedToTheSuperiorContext() throws MoneyTransferException {
		setup();
		
		double transferAmount = 30;
		
		MoneyTransferParameters parametersMock = mockFactory.getMoneyTransferParametersMock(transferAmount, true);
		
		Account originAccount = parametersMock.getOriginAccount();
		Account destinyAccount = parametersMock.getDestinyAccount();
		
		when(parametersMock.getAccountRepository().persistAll(originAccount, destinyAccount))
			.thenThrow(RuntimeException.class);
		
		service.executeMoneyTransfer(parametersMock);
	}
}
