package br.com.programmers.bank.service;

public interface IMoneyTransfer {
	boolean transfer(int originAccountId, int destinyAccountId, double amount);
}
