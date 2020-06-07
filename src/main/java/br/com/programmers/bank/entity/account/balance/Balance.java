package br.com.programmers.bank.entity.account.balance;

import br.com.programmers.bank.entity.account.ICreditOperation;
import br.com.programmers.bank.entity.account.IDebitOperation;

public abstract class Balance implements ICreditOperation, IDebitOperation {
	public abstract double getAmount();
	public abstract Balance copy();
	public abstract int hashCode();
	public abstract boolean equals(Object obj);
}
