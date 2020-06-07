package br.com.programmers.bank.entity.account.balance.impl;

import static br.com.programmers.bank.commons.utils.AssertUtils.assertDoubleMinimumValue;
import static java.lang.String.format;

import br.com.programmers.bank.entity.account.balance.Balance;

public class NonNegativeBalance extends Balance {
	
	private static final double SMALLEST_CENTS_UNIT = 0.01;
	private static final double ZERO = 0;
	private static final String NEGATIVE_INITIAL_BALANCE_AMOUNT = "The balance cannot be initialized with negative values";
	private static final String INVALID_MIN_AMOUNT_TO_BE_CREDITED = "The amount for the balance credit must be of at least one cent. The informed amount was: < %s >";
	private static final String INVALID_MIN_AMOUNT_TO_BE_DEBITED = "The amount for the balance debit must be of at least one cent. The informed amount was: < %s >";
	private static final String NOT_ENOUGH_BALANCE_FOR_DEBIT = "Theres not enough balance for the debit operation";

	private double amount;
	
	public NonNegativeBalance() {
	}

	public NonNegativeBalance(double initialAmount) {
		
		assertDoubleMinimumValue(initialAmount, ZERO, NEGATIVE_INITIAL_BALANCE_AMOUNT);
		
		this.amount = initialAmount;
	}
	
	@Override
	public void credit(double creditAmount) {

		validateCreditOperation(creditAmount);
		
		this.amount += creditAmount;
	}
	
	private void validateCreditOperation(double creditAmount) {
		
		assertDoubleMinimumValue(creditAmount, SMALLEST_CENTS_UNIT, format(INVALID_MIN_AMOUNT_TO_BE_CREDITED, creditAmount));
	}
	
	@Override
	public void debit(double debitAmount) {
		
		validateDebitOperation(debitAmount);
		
		this.amount -= debitAmount;
	}
	
	private void validateDebitOperation(double debitAmount) {
		
		assertDoubleMinimumValue(debitAmount, SMALLEST_CENTS_UNIT, format(INVALID_MIN_AMOUNT_TO_BE_DEBITED, debitAmount));
		
		double debitOperationResult = this.amount - debitAmount;
		
		assertDoubleMinimumValue(debitOperationResult, ZERO, format(NOT_ENOUGH_BALANCE_FOR_DEBIT, debitAmount));
	}

	@Override
	public double getAmount() {
		return this.amount;
	}

	@Override
	public Balance copy() {
		return new NonNegativeBalance(this.amount);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NonNegativeBalance other = (NonNegativeBalance) obj;
		if (Double.doubleToLongBits(amount) != Double.doubleToLongBits(other.amount))
			return false;
		return true;
	}
}
