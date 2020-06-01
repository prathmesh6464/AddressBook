package com.bridz.address_book.service;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.bridz.address_book.model.AddressBook;
import com.bridz.address_book.model.Person;
import com.bridz.address_book.pattern.GetInstance;
import com.bridz.address_book.utility.PersonDetailsSetter;
import com.bridz.address_book.controller.AddressBookController;
import com.bridz.address_book.exception.AddressBookException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DisplayPersonOperationImplementation implements DisplayPersonOperation {

	private List<AddressBook> addressBookList = new ArrayList<AddressBook>();
	private Scanner scanner = GetInstance.INSTANCE.getScannerInstance();
	private File file = GetInstance.INSTANCE.getFileInstance();
	private PersonDetailsSetter personDetailsSetter = GetInstance.INSTANCE.getPersonDetailsSetterInstance();
	private AddressBookController addressBookController = GetInstance.INSTANCE.getAddressBookControllerInstance();

	private ObjectMapper objectMapper = GetInstance.INSTANCE.getObjectMapperInstance();

	private int chosedOption;
	private int idIndex = 1;

	public void displayPersonOperations() {

		InputStream inputStream;

		try {
			inputStream = GetInstance.INSTANCE.getFileInputStreamInstance();
			TypeReference<List<AddressBook>> typeReference = new TypeReference<List<AddressBook>>() {
			};

			List<AddressBook> addressBooks = objectMapper.readValue(inputStream, typeReference);

			if (addressBooks.isEmpty()) {

				System.out.println("Address book is empty");
				addressBookController.start();
			}

			for (AddressBook eachAddressBook : addressBooks) {

				System.out.println(eachAddressBook.getId() + " " + eachAddressBook.getName());
			}

			System.out.println("\nSelect address book number for open person's list");

			int chooseAddressBook;

			try {
				chooseAddressBook = scanner.nextInt();
			} catch (InputMismatchException inputMismatchException) {

				throw new AddressBookException(500, "Invlid input please input");
			}

			if (chooseAddressBook != 0) {

				System.out.println("\nChoose one number for person details operation");
				System.out.println("\n" + "*******Welcome to address book*******" + "\n");
				System.out.println("1. Create person");
				System.out.println("2. Open person");
				System.out.println("3. Update person");
				System.out.println("4. Delete person");
				System.out.println("5. Quit");

				try {
					chosedOption = scanner.nextInt();
				} catch (InputMismatchException inputMismatchException) {

					throw new AddressBookException(500, "Invlid input please input");
				}

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
							throw new AddressBookException(501, "File Not Found");
						}

						inputStream.close();
						this.displayPersonOperations();
						break;

					case 2:

						if (eachAddressBook2.getPersons().isEmpty()) {

							System.out.println("Person list is empty");
							addressBookController.start();
						}

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

						int index2 = 0;
						
						System.out.println("Please Enter person number to update.");

						int updatePersonNumber;
						try {
							updatePersonNumber = scanner.nextInt();
						} catch (InputMismatchException inputMismatchException) {

							throw new AddressBookException(500, "Invlid input please input");
						}

						for (Person person : eachAddressBook2.getPersons()) {

							if (person.getId() == updatePersonNumber) {

								System.out.println("Please Enter person details to update.\n");
								eachAddressBook2.getPersons().remove(index2);
								eachAddressBook2
										.setPersons(personDetailsSetter.personDetailsSetter(updatePersonNumber));
								objectMapper.writeValue(file, addressBooks);
								System.out.println("Person details updated successfully!!");

								this.displayPersonOperations();
								updatePersonNumber = -1;
							}
							
							index2++;
						}

						if (updatePersonNumber != -1) {
							System.out.println("Please enter valid number to update address book");
							this.displayPersonOperations();
						}
						inputStream.close();
						break;

					case 4:
						int index = 0;
						System.out.println("Please Enter person number to delete.");

						int deletePersonNumber = scanner.nextInt();

						for (Person person : eachAddressBook2.getPersons()) {

							if (person.getId() == deletePersonNumber) {

								eachAddressBook2.getPersons().remove(index);
								objectMapper.writeValue(file, addressBooks);
								System.out.println("Person details deleted successfully!!");
								inputStream.close();
								this.displayPersonOperations();
								updatePersonNumber = -1;
							}
							index++;
						}

						if (deletePersonNumber != -1) {
							System.out.println("Please enter valid number to delete person");
							this.displayPersonOperations();
						}

						break;

					case 5:

						addressBookController.start();

						break;

					default:
						System.out.println("Please enter valid option");
						break;
					}

				}
			}

			if (System.in.read() != -1) {
				this.displayPersonOperations();
			}

			inputStream.close();

		} catch (Exception exception) {

			throw new AddressBookException(501, "File file found");
		}

	}
}
