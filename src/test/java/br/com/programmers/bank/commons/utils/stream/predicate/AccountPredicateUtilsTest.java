package br.com.programmers.bank.commons.utils.stream.predicate;

import static br.com.programmers.bank.commons.utils.stream.predicate.AccountPredicateUtils.hasTheAccountTheGivenAccountId;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import br.com.programmers.bank.entity.account.impl.Account;
import br.com.programmers.bank.mock.MockFactory;

@RunWith(BlockJUnit4ClassRunner.class)
public class AccountPredicateUtilsTest {
	
	private MockFactory mockFactory = MockFactory.getMockFactory();

	@Test
	public void testMustReturnTrueWhenTheAccountIdAndTheGivenIdAreTheSame() {
		int accountId = ThreadLocalRandom.current().nextInt(19);
		double balanceAmount = ThreadLocalRandom.current().nextDouble();
		Account accountMock = mockFactory.getAccountMock(accountId, balanceAmount);
		
		boolean isTheSameAccount = hasTheAccountTheGivenAccountId.test(accountMock, accountId);
		
		assertTrue(isTheSameAccount);
	}
	
	@Test
	public void testMustReturnFalseWhenTheAccountIdAndTheGivenIdAreDifferent() {
		int accountId = 2;
		double balanceAmount = ThreadLocalRandom.current().nextDouble(30);
		Account accountMock = mockFactory.getAccountMock(accountId, balanceAmount);
		int testId = 3;
		
		boolean isTheSameAccount = hasTheAccountTheGivenAccountId.test(accountMock, testId);
		
		assertFalse(isTheSameAccount);
	}
	
}
