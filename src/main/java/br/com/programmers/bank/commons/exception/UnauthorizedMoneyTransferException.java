package br.com.programmers.bank.commons.exception;

/**
 * <p>Exception derived from {@code RuntimeException} destined to indicate an unauthorized attempt of money transference</p>
 * @author  Giovani Ugolini
 */
public class UnauthorizedMoneyTransferException extends RuntimeException {
	
	private static final long serialVersionUID = -4803900102702012408L;
	private static final String DEFAULT_EXCEPTION_MESSAGE = "An unauthorized attempt to a money transference occured";

	/** 
	 * Constructs a new {@code UnauthorizedMoneyTransferException} instance with a default message using the corresponding {@code RuntimeException} constructor
     */
	public UnauthorizedMoneyTransferException() {
		new UnauthorizedMoneyTransferException(DEFAULT_EXCEPTION_MESSAGE);
	}

	/** 
	 * Constructs a new {@code UnauthorizedMoneyTransferException} using the corresponding {@code RuntimeException} constructor
     */
	public UnauthorizedMoneyTransferException(String message) {
		super(message);
	}

	/** 
	 * Constructs a new {@code UnauthorizedMoneyTransferException} using the corresponding {@code RuntimeException} constructor
     */
	public UnauthorizedMoneyTransferException(Throwable cause) {
		super(cause);
	}

	/** 
	 * Constructs a new {@code UnauthorizedMoneyTransferException} using the corresponding {@code RuntimeException} constructor
     */
	public UnauthorizedMoneyTransferException(String message, Throwable cause) {
		super(message, cause);
	}

	/** 
	 * Constructs a new {@code UnauthorizedMoneyTransferException} using the corresponding {@code RuntimeException} constructor
     */
	public UnauthorizedMoneyTransferException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
