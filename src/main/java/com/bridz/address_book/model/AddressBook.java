package com.bridz.address_book.model;

import java.util.ArrayList;
import java.util.List;

public class AddressBook {

	String name;
	long id;
		
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

	public List<Person> getPerson() {
		return this.persons;
	}

	public void setPerson(List<Person> person) {
		this.persons = person;
	}
	
}
