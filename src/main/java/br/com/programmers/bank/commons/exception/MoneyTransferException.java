package br.com.programmers.bank.commons.exception;

/**
 * <p>Exception derived from {@code Exception} destined to indicate problems occurred during money transference processes</p>
 * @author  Giovani Ugolini
 */
public class MoneyTransferException extends Exception {

	private static final long serialVersionUID = -4237640380479935810L;
	private static final String DEFAULT_MESSAGE = "An exception occurred during the money transfer process";

	/** 
	 * Constructs a new {@code MoneyTransferException} instance with a default message using the corresponding {@code Exception} constructor
     */
	public MoneyTransferException() {
		new MoneyTransferException(DEFAULT_MESSAGE);
	}

	/** 
	 * Constructs a new {@code MoneyTransferException} instance using the corresponding {@code Exception} constructor
     */
	public MoneyTransferException(String message) {
		super(message);
	}

	/** 
	 * Constructs a new {@code MoneyTransferException} instance using the corresponding {@code Exception} constructor
     */
	public MoneyTransferException(Throwable cause) {
		super(cause);
	}

	/** 
	 * Constructs a new {@code MoneyTransferException} instance using the corresponding {@code Exception} constructor
     */
	public MoneyTransferException(String message, Throwable cause) {
		super(message, cause);
	}

	/** 
	 * Constructs a new {@code MoneyTransferException} instance using the corresponding {@code Exception} constructor
     */
	public MoneyTransferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
