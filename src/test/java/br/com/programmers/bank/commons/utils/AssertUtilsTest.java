package br.com.programmers.bank.commons.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertSame;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(BlockJUnit4ClassRunner.class)
public class AssertUtilsTest {
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();	

	@Test
	public void testMustThrowAnExceptionInNullValueDuringNotNullAssertion() {
		Object objMock = null;
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.notNull(objMock, messageMock);
	}
	
	@Test
	public void testMustReturnTheGivenObjectIfNotNullDuringNotNullAssertion() {
		String objMock = "mock";
		String messageMock = "test";
		
		String response = AssertUtils.notNull(objMock, messageMock);
		
		assertSame(objMock, response);
	}
	
	@Test
	public void testMustThrowAnExceptionInNullValueDuringNotBlankAssertion() {
		String stringMock = null;
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.notBlank(stringMock, messageMock);
	}
	
	@Test
	public void testMustThrowAnExceptionInEmptyValueDuringNotBlankAssertion() {
		String stringMock = "";
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.notBlank(stringMock, messageMock);
	}
	
	@Test
	public void testMustThrowAnExceptionInBlankValueDuringNotBlankAssertion() {
		String stringMock = "   ";
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.notBlank(stringMock, messageMock);
	}
	
	@Test
	public void testMustReturnTheInputStringIfValidDuringNotBlankAssertion() {
		String stringMock = "mock";
		String messageMock = "test";
		
		String response = AssertUtils.notBlank(stringMock, messageMock);
		
		assertSame(stringMock, response);
	}
	
	@Test
	public void testMustThrowAnExceptionIfADoubleValueIsLesserThanTheMinValueDuringDoubleMinAssertion() {
		double value = 1;
		double min = 2;
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.assertDoubleMinimumValue(value, min, messageMock);
	}
	
	@Test
	public void testMustReturnTheGivenDoubleValueIfItsEqualToTheMinValueDuringDoubleMinAssertion() {
		double value = 2;
		double min = 2;
		String messageMock = "test";
		
		double response = AssertUtils.assertDoubleMinimumValue(value, min, messageMock);
		
		assertThat(min).isEqualTo(value);
		assertThat(value).isEqualTo(response);
	}
	
	@Test
	public void testMustReturnTheGivenDoubleValueIfItsGreaterThanTheMinValueDuringDoubleMinAssertion() {
		double value = 3;
		double min = 2;
		String messageMock = "test";
		
		double response = AssertUtils.assertDoubleMinimumValue(value, min, messageMock);
		
		assertThat(min).isLessThan(value);
		assertThat(value).isEqualTo(response);
	}
	
	@Test
	public void testMustThrowAnExceptionIfALongValueIsLesserThanTheMinValueDuringLongMinAssertion() {
		long value = 1;
		long min = 2;
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.assertLongMinimumValue(value, min, messageMock);
	}
	
	@Test
	public void testMustReturnTheGivenLongValueIfItsEqualToTheMinValueDuringLongMinAssertion() {
		long value = 2;
		long min = 2;
		String messageMock = "test";
		
		long response = AssertUtils.assertLongMinimumValue(value, min, messageMock);
		
		assertThat(min).isEqualTo(value);
		assertThat(value).isEqualTo(response);
	}
	
	@Test
	public void testMustReturnTheGivenLongValueIfItsGreaterThanTheMinValueDuringLongMinAssertion() {
		long value = 3;
		long min = 2;
		String messageMock = "test";
		
		long response = AssertUtils.assertLongMinimumValue(value, min, messageMock);
		
		assertThat(min).isLessThan(value);
		assertThat(value).isEqualTo(response);
	}
	
	@Test
	public void testMustThrowAnExceptionInEmptyCollectionValueDuringNotEmptyAssertion() {
		Collection<Integer> collectionMock = new LinkedList<>();
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.notEmpty(collectionMock, messageMock);
	}
	
	@Test
	public void testMustThrowAnExceptionInNullCollectionValueDuringNotEmptyAssertion() {
		Collection<Integer> collectionMock = null;
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.notEmpty(collectionMock, messageMock);
	}
	
	@Test
	public void testMustReturnTheGivenCollectionsIfItHasAtLeastOneElementDuringNotEmptyAssertion() {
		Set<Integer> setMock = new LinkedHashSet<>();
		setMock.add(3);
		
		String messageMock = "test";
		
		Set<Integer> response = AssertUtils.notEmpty(setMock, messageMock);
		
		assertSame(setMock, response);
	}
	
	@Test
	public void testMustThrowAnExceptionIfTheFirstObjectIsNullInNotEqualsAssertion() {
		String obj1 = null;
		String obj2 = "mock";
		String messageMock = ReflectionTestUtils.getField(AssertUtils.class, "NULL_EQUALS_OBJECT").toString();
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
				
		AssertUtils.notEquals(obj1, obj2, messageMock);
	}
	
	@Test
	public void testMustThrowAnExceptionIfTheObjectsAreEqualToEachOtherDuringInNotEqualsAssertion() {
		Set<Integer> setMock1 = new LinkedHashSet<>();
		setMock1.add(3);
		
		Set<Integer> setMock2 = new LinkedHashSet<>();
		setMock2.add(3);
		
		String messageMock = "test";
		
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage(messageMock);
		
		AssertUtils.notEquals(setMock1, setMock2, messageMock);
	}
	
	@Test
	public void testMustDoNothingIfTheObjectsAreNotEqualToEachOtherDuringInNotEqualsAssertion() {
		Set<Integer> setMock1 = new LinkedHashSet<>();
		setMock1.add(3);
		
		Set<Integer> setMock2 = new LinkedHashSet<>();
		setMock2.add(2);
		
		String messageMock = "test";
		
		AssertUtils.notEquals(setMock1, setMock2, messageMock);
	}
}
