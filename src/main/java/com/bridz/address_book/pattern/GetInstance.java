package com.bridz.address_book.pattern;

import java.util.Scanner;

import com.bridz.address_book.model.AddressBook;
import com.bridz.address_book.model.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.bridz.address_book.model.PersonDetailsSetter;

public enum GetInstance {

	INSTANCE;

	private String urlOfJsonFile = "C:\\Users\\King\\Documents\\workspace-spring-tool-suite-4-4.6.0.RELEASE\\AddressBook\\AddressBookJsonFile\\AddressBook.json";

	public Scanner getScannerInstance() {
		return new Scanner(System.in);
	}

	public File getFileInstance() {

		return new File(this.urlOfJsonFile);
	}

	public AddressBook getAddressBookInstance() {
		return new AddressBook();
	}

	public Person getPersonInstance() {
		return new Person();
	}

	public ObjectMapper getObjectMapperInstance() {
		return new ObjectMapper();
	}

	public PersonDetailsSetter getPersonDetailsSetterInstance() {
		return new PersonDetailsSetter();
	}

	public FileInputStream getFileInputStreamInstance() throws FileNotFoundException {
		return new FileInputStream(this.getFileInstance());
	}
}
