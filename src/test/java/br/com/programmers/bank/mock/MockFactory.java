package br.com.programmers.bank.mock;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import org.mockito.Mockito;

import br.com.programmers.bank.entity.account.balance.Balance;
import br.com.programmers.bank.entity.account.balance.impl.DefaultBalance;
import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.entity.person.impl.Person;
import br.com.programmers.bank.model.transfer.money.MoneyTransferParameters;
import br.com.programmers.bank.repository.IAccountRepository;

public class MockFactory {

	private MockFactory() {}
	
	public static MockFactory getMockFactory() {
		return new MockFactory();
	}
	
	public DefaultBalance getDefaultBalanceMock(double amount) {
		return new DefaultBalance(amount);
	}
	
	public Set<Account> getAccountSetMock(int accountAmount, double balanceAmount) {
		Set<Account> accountSet = new HashSet<>();
			
		for (int i = 0; i < accountAmount; i++) {
			Account accountMock = getAccountMock(i, balanceAmount);
			accountSet.add(accountMock);
		}

		return accountSet;
	}

	public Account getAccountMock(int accountId, double balanceAmount) {
		Balance balanceMock = getDefaultBalanceMock(balanceAmount);
		return new Account(balanceMock, accountId, "agency_mock");
	}

	public Person getPersonMock() {
		return getPersonMock(ThreadLocalRandom.current().nextLong(10), "person_name_mock");
	}
	
	public Person getPersonMock(long id, String name) {
		return new Person(id, name);
	}
	
	public MoneyTransferParameters getMoneyTransferParametersMock(double amount, boolean mockedRepository) {
		return MoneyTransferParameters.builder()
			.amount(amount)
			.destinyAccount(getAccountMock(2, amount))
			.originAccount(getAccountMock(3, amount))
			.accountRepository(mockedRepository? getIAccountRepositoryMock() : getIAccountRepositoryImplMock())
			.build();
	}
	
	public IAccountRepository getIAccountRepositoryMock() {
		return Mockito.mock(IAccountRepository.class);
	}
	
	public IAccountRepository getIAccountRepositoryImplMock() {
		return new IAccountRepository() {
			
			@Override
			public List<Account> persistAll(Account... accounts) {
				return new LinkedList<>();
			}
			
			@Override
			public Account getAccountById(int id) {
				return getAccountMock(id, 30);
			}
		};
	}
}
