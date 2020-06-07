package br.com.programmers.bank.commons.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

@RunWith(BlockJUnit4ClassRunner.class)
public class ExceptionSupplierTest {

	@Test
	public void testMustThrowAnExceptionInNullValueDuringNotNullAssertion() {
		int accountId = ThreadLocalRandom.current().nextInt();
		
		Supplier<?> response = ExceptionSupplier.accountNotFoundById(accountId);
		
		Object responseObject = response.get();
		
		assertThat(responseObject).isInstanceOf(RuntimeException.class);
		RuntimeException exception = (RuntimeException) responseObject;
		assertThat(exception.getMessage()).contains(String.valueOf(accountId));
	}
}
