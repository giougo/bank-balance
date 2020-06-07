package br.com.programmers.bank.commons.utils;

import java.util.NoSuchElementException;
import java.util.function.Supplier;
import static java.lang.String.*;

/**
 * <p>
 * Utility class destined to provide exception suppliers for the application.
 * The main goals of this class involves centralizing the exception suppliers of
 * the application while increasing the readability of business logic classes.
 * </p>
 * <p>
 * Functionality is provided through the exposure of multiple public static methods - therefore,
 *  an instance of this class is not expected to be created
 * </p>
 * @author  Giovani Ugolini
 */
public final class ExceptionSupplier {
	
	private static final String ACCOUNT_NOT_FOUND_BY_ID = "The account with id < %s > was not found";

	/**
	 * Intended to not create instances
	 */
	private ExceptionSupplier() {}
	
	/**
	 * Provides a supplier parameterized with any of the {@code RuntimeException} implementations
	 * whenever an account could'nt be found for a given account id
	 * @param accountId the id for which the account could not be found
	 * @return an exception supplier containing a customized message
	 * @author Giovani Ugolini
	 */
	public static final Supplier<? extends RuntimeException> accountNotFoundById(int accountId) {
		String errorMessage = format(ACCOUNT_NOT_FOUND_BY_ID, accountId);
		return () -> new NoSuchElementException(errorMessage);
	}
}
