package com.bridz.address_book.service;

import com.bridz.address_book.pattern.GetInstance;

import com.bridz.address_book.model.AddressBook;

public class AddressBookManager {

	public void start() {
		System.out.println("*******Welcome to address book*******" + "\n");
		System.out.println("Enter number to choose option");
		System.out.println("1. Create address book");
		System.out.println("2. Open address book");
		System.out.println("3. Update address book");

		int chosedOption = GetInstance.INSTANCE.getScannerInstance().nextInt();

		switch (chosedOption) {
		case 1:
			System.out.println("Enter name for address book");
			String nameOfAddressBook = GetInstance.INSTANCE.getScannerInstance().next();
			AddressBook addressBook = new AddressBook();
			addressBook.setName(nameOfAddressBook);
			
			if (nameOfAddressBook != null) {
				System.out.println("Address book created");
			} else {
				System.out.println("Please enter valid address book name");
			}
			break;
		case 2:

			break;
		case 3:
			break;
		default:
			System.out.println("Please enter valid option");
			break;

		}

	}

}
