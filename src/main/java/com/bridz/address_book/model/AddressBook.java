package com.bridz.address_book.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

	long id;
	String name;
	List<Person> persons = new ArrayList<>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

}
