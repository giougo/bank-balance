package br.com.programmers.bank.commons.utils;

import java.util.Collection;

/**
 * <p>Utility class destined to provide utility methods for runtime assertions.</p>
 * <p>Functionality is provided through the exposure of multiple public static methods - therefore,
 *  an instance of this class is not expected to be created</p>
 * @author  Giovani Ugolini
 */
public final class AssertUtils {
	
	private static final String NULL_EQUALS_OBJECT = "The object to invoke equals cannot be null for the assertion";
	
	/**
	 * Intended to not create instances
	 */
	private AssertUtils() {}

	/**
	 * Assert that the given object is not null. If null, throws an {@code IllegalArgumentException}
	 * @param <T> the type of the object
	 * @param object the object to be tested
	 * @param errorMessage the error message to be used as the detail message of the exception if the assertion fails
	 * @return the tested object itself
	 * @throws IllegalArgumentException whenever the given element is null
	 * @author Giovani Ugolini
	 */
	public static final <T> T notNull(final T object, String errorMessage) {
		if (object == null) {
			throw new IllegalArgumentException(errorMessage);
		}
		
		return object;
	}
	
	/**
	 * Assert that the given string is not null or blank. If null or blank, throws an {@code IllegalArgumentException}
	 * @param text the string to be tested
	 * @param errorMessage the error message to be used as the detail message of the exception if the assertion fails
	 * @return the tested string itself
	 * @throws IllegalArgumentException whenever the given string is null or blank
	 * @author Giovani Ugolini
	 */
	public static final String notBlank(final String text, String errorMessage) {
		notNull(text, errorMessage);
		
		if (text.isBlank()) {
			throw new IllegalArgumentException(errorMessage);
		}
		
		return text;
	}
	
	/**
	 * Assert that a given double value is greater than another given double minimum value
	 * @param value the double value to be tested
	 * @param min the double minimum value to be tested
	 * @param errorMessage the error message to be used as the detail message of the exception if the assertion fails
	 * @return the tested object itself
	 * @throws IllegalArgumentException whenever the value is lesser than the min
	 * @author Giovani Ugolini
	 */
	public static final double assertDoubleMinimumValue(final double value, final double min, String errorMessage) {
		int equality = Double.compare(value, min);
		
		if (equality < 0) {
			throw new IllegalArgumentException(errorMessage);
		}
		
		return value;
	}
	
	/**
	 * Assert that a given long value is greater than another given long minimum value
	 * @param value the long value to be tested
	 * @param min the long minimum value to be tested
	 * @param errorMessage the error message to be used as the detail message of the exception if the assertion fails
	 * @return the tested long value itself
	 * @throws IllegalArgumentException whenever the value is lesser than the min
	 * @author Giovani Ugolini
	 */
	public static final long assertLongMinimumValue(final long value, final long min, String errorMessage) {
		if (value < min) {
			throw new IllegalArgumentException(errorMessage);
		}
		
		return value;
	}
	
	/**
	 * Assert that a given collection implementation is not null or empty
	 * @param <T> the type of the collection implementation
	 * @param <S> a super type of the original collection element type
	 * @param collection the collection to be tested
	 * @param errorMessage the error message to be used as the detail message of the exception if the assertion fails
	 * @return the collection implementation itself
	 * @throws IllegalArgumentException whenever the collection is null or empty
	 * @author Giovani Ugolini
	 */
	public static final <S, T extends Collection<? super S>> T notEmpty(final T collection, String errorMessage) {
		notNull(collection, errorMessage);
		
		if (collection.isEmpty()) {
			throw new IllegalArgumentException(errorMessage);
		}
		return collection;
	}
	
	/**
	 * Assert that a given object is not equals to another through the invocation of the equals method of the first object on the second
	 * @param obj1 the first object to be tested
	 * @param obj2 the second object to be tested
	 * @param errorMessage the error message to be used as the detail message of the exception if the assertion fails
	 * @throws IllegalArgumentException whenever the first object is null
	 * @throws IllegalArgumentException whenever the objects are equal
	 * @author Giovani Ugolini
	 */
	public static final void notEquals(final Object obj1, final Object obj2, String errorMessage) {
		notNull(obj1, NULL_EQUALS_OBJECT);
		boolean isEqual = obj1.equals(obj2);
		
		if (isEqual) {
			throw new IllegalArgumentException(errorMessage);
		}
	}
}
