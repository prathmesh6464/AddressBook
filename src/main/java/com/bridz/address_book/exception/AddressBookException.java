package com.bridz.address_book.exception;

public class AddressBookException extends RuntimeException {

	private static final long serialVersionUID = -4005000293040820290L;

	int errorCode;

	// Constructor @param message
	public AddressBookException(int errorCode, String message) {

		super(message);
		this.errorCode = errorCode;

	}

}
