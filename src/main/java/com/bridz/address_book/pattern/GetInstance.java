package com.bridz.address_book.pattern;

import java.util.Scanner;

import com.bridz.address_book.controller.AddressBookController;
import com.bridz.address_book.model.AddressBook;
import com.bridz.address_book.model.Person;
import com.bridz.address_book.service.DisplayPersonOperationImplementation;
import com.bridz.address_book.utility.PersonDetailsSetter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;

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

	public AddressBookController getAddressBookControllerInstance() {
		return new AddressBookController();
	}

	public PersonDetailsSetter getPersonDetailsSetterInstance() {
		return new PersonDetailsSetter();
	}
	
	public DisplayPersonOperationImplementation getDisplayPersonOperationInstance() {
		return new DisplayPersonOperationImplementation();
	}

	public FileInputStream getFileInputStreamInstance() throws FileNotFoundException {
		return new FileInputStream(this.getFileInstance());
	}
}
