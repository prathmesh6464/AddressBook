package com.bridz.address_book.controller;

import com.bridz.address_book.service.AddressBookManager;
import com.bridz.address_book.service.AddressBookManagerImplementation;

public class AddressBookController {

	public void start() {
		AddressBookManager addressBookManager = new AddressBookManagerImplementation();
		addressBookManager.displayAddressBookService();
	}

}
