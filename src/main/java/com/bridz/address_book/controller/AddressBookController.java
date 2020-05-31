package com.bridz.address_book.controller;

import com.bridz.address_book.service.AddressBookManager;

public class AddressBookController {

	public void start() {
		AddressBookManager addressBookManager = new AddressBookManager();
		addressBookManager.start();
	}

}
