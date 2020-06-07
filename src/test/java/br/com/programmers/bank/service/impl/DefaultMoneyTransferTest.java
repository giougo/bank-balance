package br.com.programmers.bank.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.mockito.ArgumentCaptor;

import br.com.programmers.bank.commons.exception.MoneyTransferException;
import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.mock.MockFactory;
import br.com.programmers.bank.model.transfer.money.MoneyTransferParameters;
import br.com.programmers.bank.repository.IAccountRepository;

@RunWith(BlockJUnit4ClassRunner.class)
public class DefaultMoneyTransferTest {

	private DefaultMoneyTransfer moneyTransfer;
	private IAccountRepository accountRepository;
	private DefaultMoneyTransferExecutor moneyTransferExecutor;
	
	private ArgumentCaptor<MoneyTransferParameters> captor;
	
	private static MockFactory mockFactory = MockFactory.getMockFactory();

	public void setup() {
		captor = ArgumentCaptor.forClass(MoneyTransferParameters.class);
		
		moneyTransferExecutor = mock(DefaultMoneyTransferExecutor.class);
		accountRepository = mock(IAccountRepository.class);
		
		moneyTransfer = new DefaultMoneyTransfer(accountRepository, moneyTransferExecutor);
	}
	
	@Test
	public void testMustReturnTrueIfAllAccountsWereFoundAndTheTransferenceProcessOccurredWithoutExceptions() throws MoneyTransferException {
		setup();
		
		int originAccountId = 1;
		int destinyAccountId = 2;
		double amount = 25;
		
		Account originAccountMock = mockFactory.getAccountMock(originAccountId, 30);
		Account destinyAccountMock = mockFactory.getAccountMock(destinyAccountId, 40);
		
		when(accountRepository.getAccountById(originAccountId))
			.thenReturn(originAccountMock);
		
		when(accountRepository.getAccountById(destinyAccountId))
			.thenReturn(destinyAccountMock);
		
		doNothing().when(moneyTransferExecutor)
			.executeMoneyTransfer(captor.capture());
		
		boolean response = moneyTransfer.transfer(originAccountId, destinyAccountId, amount);
		
		assertTrue(response);
		
		MoneyTransferParameters transferParameters = captor.getValue();
		
		assertSame(accountRepository, transferParameters.getAccountRepository());
		assertThat(amount).isEqualTo(transferParameters.getAmount());
		assertEquals(originAccountMock, transferParameters.getOriginAccount());
		assertEquals(destinyAccountMock, transferParameters.getDestinyAccount());
	}
	
	@Test(expected = RuntimeException.class)
	public void testMustThrowAnExceptionIfTheOriginAccountCouldNotBeFound() throws MoneyTransferException {
		setup();
		
		int originAccountId = 1;
		int destinyAccountId = 2;
		double amount = 25;
		
		when(accountRepository.getAccountById(originAccountId))
			.thenReturn(null);
		
		moneyTransfer.transfer(originAccountId, destinyAccountId, amount);
	}
	
	@Test(expected = RuntimeException.class)
	public void testMustThrowAnExceptionIfTheDestinyAccountCouldNotBeFound() throws MoneyTransferException {
		setup();
		
		int originAccountId = 1;
		int destinyAccountId = 2;
		double amount = 25;
		
		Account originAccountMock = mockFactory.getAccountMock(originAccountId, 30);
		
		when(accountRepository.getAccountById(originAccountId))
			.thenReturn(originAccountMock);
		
		when(accountRepository.getAccountById(destinyAccountId))
			.thenReturn(null);
		
		moneyTransfer.transfer(originAccountId, destinyAccountId, amount);
	}
	
	@Test
	public void testIfAnExceptionIsThrownByTheTransferenceExecutorAFalseReturnMustBeProvided() throws MoneyTransferException {
		setup();
		
		int originAccountId = 1;
		int destinyAccountId = 2;
		double amount = 25;
		
		Account originAccountMock = mockFactory.getAccountMock(originAccountId, 30);
		Account destinyAccountMock = mockFactory.getAccountMock(destinyAccountId, 40);
		
		when(accountRepository.getAccountById(originAccountId))
			.thenReturn(originAccountMock);
		
		when(accountRepository.getAccountById(destinyAccountId))
			.thenReturn(destinyAccountMock);
		
		doThrow(MoneyTransferException.class).when(moneyTransferExecutor)
			.executeMoneyTransfer(captor.capture());
		
		boolean response = moneyTransfer.transfer(originAccountId, destinyAccountId, amount);
		
		assertFalse(response);
		
		MoneyTransferParameters transferParameters = captor.getValue();
		
		assertSame(accountRepository, transferParameters.getAccountRepository());
		assertThat(amount).isEqualTo(transferParameters.getAmount());
		assertEquals(originAccountMock, transferParameters.getOriginAccount());
		assertEquals(destinyAccountMock, transferParameters.getDestinyAccount());
	}
	
	@Test(expected = RuntimeException.class)
	public void testMustThrowAnExceptionIfANullMoneyTransferExecutorIsUsedAsAConstructorArgument() {
		moneyTransferExecutor = null;
		accountRepository = mock(IAccountRepository.class);
		
		moneyTransfer = new DefaultMoneyTransfer(accountRepository, moneyTransferExecutor);
	}
	
	@Test(expected = RuntimeException.class)
	public void testMustThrowAnExceptionIfANullAccountRepositoryIsUsedAsAConstructorArgument() {
		moneyTransferExecutor = mock(DefaultMoneyTransferExecutor.class);;
		accountRepository = null;
		
		moneyTransfer = new DefaultMoneyTransfer(accountRepository, moneyTransferExecutor);
	}
}
