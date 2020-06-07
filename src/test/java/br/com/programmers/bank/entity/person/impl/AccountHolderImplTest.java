package br.com.programmers.bank.entity.person.impl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import br.com.programmers.bank.commons.exception.UnauthorizedMoneyTransferException;
import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.mock.MockFactory;
import br.com.programmers.bank.service.IMoneyTransfer;

@RunWith(BlockJUnit4ClassRunner.class)
public class AccountHolderImplTest {
	
	private static final double MOCK_AMOUNT = 20;
	private static final int MOCK_ACCOUNT_ID = 1;
	
	private AccountHolderImpl accountHolder;
	
	private Person person;
	private Set<Account> accounts;
	private IMoneyTransfer moneyTransfer;
	
	private MockFactory mockFactory = MockFactory.getMockFactory();

	public void setup() {
		Account accountMock = mockFactory.getAccountMock(MOCK_ACCOUNT_ID, MOCK_AMOUNT);
		
		person = mockFactory.getPersonMock();
		moneyTransfer = mock(IMoneyTransfer.class);
		accounts = Set.of(accountMock);
		
		accountHolder = new AccountHolderImpl(person, accounts, moneyTransfer);
	}
	
	@Test(expected = Test.None.class)
	public void testMustDispatchATransferRequestWheneverTheTransferenceIsValid() {
		setup();
		
		int destinyAccountId = 9;
		
		when(moneyTransfer.transfer(MOCK_ACCOUNT_ID, destinyAccountId, MOCK_AMOUNT))
			.thenReturn(true);
		
		accountHolder.transfer(MOCK_ACCOUNT_ID, destinyAccountId, MOCK_AMOUNT);
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void mustThrowAnExceptionIfTheTransferMoneyServiceIsNotAvailable() {
		Account accountMock = mockFactory.getAccountMock(MOCK_ACCOUNT_ID, MOCK_AMOUNT);
		
		person = mockFactory.getPersonMock();
		moneyTransfer = null;
		accounts = Set.of(accountMock);
		
		accountHolder = new AccountHolderImpl(person, accounts, moneyTransfer);
		
		int destinyAccountId = 9;
		
		accountHolder.transfer(MOCK_ACCOUNT_ID, destinyAccountId, MOCK_AMOUNT);
	}
	
	@Test(expected = UnauthorizedMoneyTransferException.class)
	public void mustThrowAnExceptionIfTheOriginAccountDoesNotBelongToTheAccountHolderInvokingTheTransference() {
		Account accountMock = mockFactory.getAccountMock(MOCK_ACCOUNT_ID, MOCK_AMOUNT);
		
		person = mockFactory.getPersonMock();
		moneyTransfer = mock(IMoneyTransfer.class);
		accounts = Set.of(accountMock);
		
		accountHolder = new AccountHolderImpl(person, accounts, moneyTransfer);
		
		int destinyAccountId = 9;
		
		accountHolder.transfer(MOCK_ACCOUNT_ID + 1, destinyAccountId, MOCK_AMOUNT);
	}
}
