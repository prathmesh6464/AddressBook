package com.bridz.address_book;

import com.bridz.address_book.controller.AddressBookController;

public class App {

	public static void main(String[] args) {

		AddressBookController addressBook = new AddressBookController();
		addressBook.start();
	}
}
