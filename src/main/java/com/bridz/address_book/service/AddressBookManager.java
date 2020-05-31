package com.bridz.address_book.service;

import com.bridz.address_book.pattern.GetInstance;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import com.bridz.address_book.model.AddressBook;
import java.io.File;
import java.util.Scanner;
import java.io.InputStream;
import com.fasterxml.jackson.core.type.TypeReference;
import com.bridz.address_book.model.Person;

public class AddressBookManager {

	List<AddressBook> addressBookList = new ArrayList<AddressBook>();
	Scanner scanner = GetInstance.INSTANCE.getScannerInstance();
	File file = GetInstance.INSTANCE.getFileInstance();
	int chosedOption;
	int idIndex = 1;

	public void start() {
		System.out.println("\n" + "*******Welcome to address book*******" + "\n");
		System.out.println("Enter number to choose option");
		System.out.println("1. Create address book");
		System.out.println("2. Open address book");
		System.out.println("3. Update address book");
		System.out.println("4. Delete address book");

		try {
			chosedOption = scanner.nextInt();
		} catch (InputMismatchException inputMismatchException) {
		}

		ObjectMapper objectMapper = GetInstance.INSTANCE.getObjectMapperInstance();

		switch (chosedOption) {
		case 1:
			System.out.println("Enter name for address book");
			String nameOfAddressBook = scanner.next();
			AddressBook addressBook = GetInstance.INSTANCE.getAddressBookInstance();
			addressBook.setName(nameOfAddressBook);
			addressBook.setId(idIndex);

			Person pers = new Person();
			pers.setId(idIndex);
			pers.setAddress("ikasdfdsf");
			pers.setCity("kadsf");
			pers.setFirstName("klsdfj");
			pers.setLastName("lastName");
			pers.setZip("234");
			pers.setState("sta");
			pers.setPhoneNumber("erwtwer");

			idIndex++;

			List<Person> personList = new ArrayList<Person>();
			personList.add(pers);
			personList.add(pers);
			addressBook.setPerson(personList);
			addressBookList.add(addressBook);
			try {
				objectMapper.writeValue(file, addressBookList);
			} catch (Exception exception) {
				exception.printStackTrace();
			}

			if (nameOfAddressBook != null) {
				System.out.println("Address book created");
				this.start();
			} else {
				System.out.println("Please enter valid address book name");
				this.start();
			}

			break;

		case 2:

			System.out.println("List of address book");
			InputStream inputStream;
			try {
				inputStream = GetInstance.INSTANCE.getFileInputStreamInstance();
				TypeReference<List<AddressBook>> typeReference = new TypeReference<List<AddressBook>>() {
				};

				List<AddressBook> addressBooks = objectMapper.readValue(inputStream, typeReference);

				for (AddressBook eachAddressBook : addressBooks) {

					System.out.println(eachAddressBook.getId() + " " + eachAddressBook.getName());
					for (Person person : eachAddressBook.getPersons()) {
						if (person.getCity().equals("kasf")) {
							System.out.println(person.getCity());
							System.out.println(person.getAddress());
						}
					}
				}

				if (System.in.read() != -1) {
					this.start();
				}

				inputStream.close();

			} catch (Exception exception) {
				System.out.println(exception);
				System.out.println("File not found!!");
			}

			break;

		case 3:

			System.out.println("Please Enter address book number to update.");
			int addressBookNumber = scanner.nextInt();
			InputStream inputStreamToUpdate;
			try {
				inputStreamToUpdate = GetInstance.INSTANCE.getFileInputStreamInstance();
				TypeReference<List<AddressBook>> typeReference = new TypeReference<List<AddressBook>>() {
				};

				List<AddressBook> addressBooks = objectMapper.readValue(inputStreamToUpdate, typeReference);

				for (AddressBook eachAddressBook : addressBooks) {

					if (eachAddressBook.getId() == addressBookNumber) {

						System.out.println("Please Enter address book name to update.");
						String resetAddressBookName = scanner.next();
						eachAddressBook.setName(resetAddressBookName);
						objectMapper.writeValue(file, addressBooks);
						System.out.println("Address book updated successfully!!");
						this.start();
						addressBookNumber = -1;
					}
				}
				if (addressBookNumber != -1) {
					System.out.println("Please enter valid number to update address book");
					this.start();
				}
				inputStreamToUpdate.close();
			} catch (Exception exception) {
				System.out.println(exception);
				System.out.println("File not found!!");
			}

			break;

		case 4:

			System.out.println("Please Enter address book number to delete address book.");
			int addressBookNumberToDelete = scanner.nextInt();
			InputStream inputStreamToDelete;
			try {
				inputStreamToDelete = GetInstance.INSTANCE.getFileInputStreamInstance();
				TypeReference<List<AddressBook>> typeReference = new TypeReference<List<AddressBook>>() {
				};

				addressBookList = objectMapper.readValue(inputStreamToDelete, typeReference);

				for (AddressBook eachAddressBook : addressBookList) {

					if (eachAddressBook.getId() == addressBookNumberToDelete) {

						addressBookList.remove(addressBookNumberToDelete - 1);
						objectMapper.writeValue(file, addressBookList);
						System.out.println("Address book deleted successfully!!");
						this.start();
						addressBookNumber = -1;
					}
				}
				if (addressBookNumberToDelete != -1) {
					System.out.println("Please enter valid number to update address book");
					this.start();
				}
				inputStreamToDelete.close();
			} catch (Exception exception) {
				System.out.println(exception);
				System.out.println("File not found!!");
			}

			break;
		default:

			System.out.println("Please enter valid option");
			break;

		}

	}

}
