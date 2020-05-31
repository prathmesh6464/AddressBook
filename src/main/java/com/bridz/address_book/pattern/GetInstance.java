package com.bridz.address_book.pattern;

import java.util.Scanner;
import java.io.File;


public enum GetInstance {

	INSTANCE;

	private String urlOfJsonFile = "C:\\Users\\King\\Documents\\workspace-spring-tool-suite-4-4.6.0.RELEASE\\AddressBook\\AddressBookJsonFile\\AddressBook.json";

	public Scanner getScannerInstance() {
		return new Scanner(System.in);
	}

	public File getInstanceOfFile() {

		return new File(this.urlOfJsonFile);
	}
}
