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
import com.bridz.address_book.model.PersonDetailsSetter;

public class AddressBookManager {

	List<AddressBook> addressBookList = new ArrayList<AddressBook>();
	Scanner scanner = GetInstance.INSTANCE.getScannerInstance();
	File file = GetInstance.INSTANCE.getFileInstance();
	PersonDetailsSetter personDetailsSetter = GetInstance.INSTANCE.getPersonDetailsSetterInstance();

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

			idIndex++;

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

			InputStream inputStream;

			try {
				inputStream = GetInstance.INSTANCE.getFileInputStreamInstance();
				TypeReference<List<AddressBook>> typeReference = new TypeReference<List<AddressBook>>() {
				};

				List<AddressBook> addressBooks = objectMapper.readValue(inputStream, typeReference);

				for (AddressBook eachAddressBook : addressBooks) {

					System.out.println(eachAddressBook.getId() + " " + eachAddressBook.getName());
				}

				System.out.println("\nSelect address book number for open person's list");
				int chooseAddressBook = scanner.nextInt();
				if (chooseAddressBook != 0) {

					System.out.println("Choose one number for person details operation");
					System.out.println("\n" + "*******Welcome to address book*******" + "\n");
					System.out.println("1. Create person");
					System.out.println("2. Open person");
					System.out.println("3. Update person");
					System.out.println("4. Delete person");
					chosedOption = scanner.nextInt();

				}

				for (AddressBook eachAddressBook2 : addressBooks) {

					if (eachAddressBook2.getId() == chooseAddressBook) {

						switch (chosedOption) {

						case 1:

							eachAddressBook2.setPersons(personDetailsSetter.personDetailsSetter(idIndex));
							idIndex++;
							try {
								objectMapper.writeValue(file, addressBooks);

							} catch (Exception exception) {
								exception.printStackTrace();
							}
							inputStream.close();
							this.start();
							break;

						case 2:

							for (Person person : eachAddressBook2.getPersons()) {

								System.out.println("\n********** Person number : " + person.getId() + " **********\n");

								System.out.println("First name   : " + person.getFirstName());
								System.out.println("Last name    : " + person.getLastName());
								System.out.println("Address      : " + person.getAddress());
								System.out.println("City         : " + person.getCity());
								System.out.println("State        : " + person.getState());
								System.out.println("Zip          : " + person.getZip());
								System.out.println("Phone number : " + person.getPhoneNumber());

								System.out.println("\n********************************\n");
							}

							break;

						case 3:

							System.out.println("Please Enter person number to update.");

							int updatePersonNumber = scanner.nextInt();

							for (Person person : eachAddressBook2.getPersons()) {

								if (person.getId() == updatePersonNumber) {

									System.out.println("Please Enter person details to update.\n");
									eachAddressBook2
											.setPersons(personDetailsSetter.personDetailsSetter(updatePersonNumber));
									objectMapper.writeValue(file, addressBooks);
									System.out.println("Person details updated successfully!!");

									this.start();
									updatePersonNumber = -1;
								}
							}

							if (updatePersonNumber != -1) {
								System.out.println("Please enter valid number to update address book");
								this.start();
							}
							inputStream.close();
							break;

						case 4:

							System.out.println("Please Enter person number to delete.");

							int deletePersonNumber = scanner.nextInt();

							for (Person person : eachAddressBook2.getPersons()) {

								if (person.getId() == deletePersonNumber) {

									addressBooks.remove(person);
									objectMapper.writeValue(file, addressBooks);
									System.out.println("Person details deleted successfully!!");
									inputStream.close();
									this.start();
									updatePersonNumber = -1;
								}

							}

							if (deletePersonNumber != -1) {
								System.out.println("Please enter valid number to delete person");
								this.start();
							}

							break;

						default:
							System.out.println("Please enter valid option");

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

			} catch (

			Exception exception) {
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
