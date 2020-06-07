package br.com.programmers.bank.entity.person.impl;

import static br.com.programmers.bank.commons.utils.AssertUtils.*;

public class Person {
	
	private static final String NAME_REQUIRED = "The person name is required";
	private static final long MIN_ID_VALUE = 1L;
	private static final String INVALID_ID_MIN_VALUE = "The person id must be greater than " + MIN_ID_VALUE ;
	
	private final long id;
	private final String name;
	
	public Person(long id, String name) {
		assertLongMinimumValue(id, MIN_ID_VALUE, INVALID_ID_MIN_VALUE);
		notNull(name, NAME_REQUIRED);
		
		this.id = id;
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
}
