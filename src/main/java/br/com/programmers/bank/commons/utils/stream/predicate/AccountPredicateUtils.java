package br.com.programmers.bank.commons.utils.stream.predicate;

import java.util.function.BiPredicate;

import javax.validation.constraints.NotNull;

import br.com.programmers.bank.entity.account.impl.Account;

/**
 * <p>
 * Utility class destined to provide utility predicates for account entities of the applciation.
 * </p>
 * <p>Functionality is provided through the exposure of multiple public static predicates instances - therefore,
 *  an instance of this class is not expected to be created.
 *  </p>
 *  <p>
 * The main goals of this class involves centralizing entities predicates
 *  while increasing the readability of business logic classes.
 *  </p>
 * @author  Giovani Ugolini
 */
public final class AccountPredicateUtils {
	
	/**
	 * Intended to not create instances
	 */
	private AccountPredicateUtils() {}
	
	/**
	 * Verify if a given account has a given id
	 * @param account the account to be tested, which cannot be {@code null}
	 * @param accountId the accountId to be tested, which cannot be {@code null}
	 * @return a bi predicate with the encapsulated logic
	 * @author Giovani Ugolini
	 */
	public static final BiPredicate<Account, Integer> hasTheAccountTheGivenAccountId = (@NotNull Account account, @NotNull Integer accountId) -> accountId == account.getAccountId();
}
