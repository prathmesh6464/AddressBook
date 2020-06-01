package com.bridz.address_book.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.bridz.address_book.model.Person;
import com.bridz.address_book.pattern.GetInstance;

public class PersonDetailsSetter {

	List<Person> personList = new ArrayList<Person>();
	Scanner scanner = GetInstance.INSTANCE.getScannerInstance();
	File file = GetInstance.INSTANCE.getFileInstance();
	int chooseNumber;

	public List<Person> personDetailsSetter(int idIndex) {

		System.out.println("Enter first name : ");
		String firstName = scanner.next();

		System.out.println("Enter last name : ");
		String lastName = scanner.next();

		System.out.println("Enter address : ");
		String address = scanner.next();

		System.out.println("Enter city : ");
		String city = scanner.next();

		System.out.println("Enter state : ");
		String state = scanner.next();

		System.out.println("Enter zip : ");
		String zip = scanner.next();

		System.out.println("Enter phone number : ");
		String phoneNumber = scanner.next();

		Person person = GetInstance.INSTANCE.getPersonInstance();
		person.setId(idIndex);
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setAddress(address);
		person.setCity(city);
		person.setState(state);
		person.setZip(zip);
		person.setPhoneNumber(phoneNumber);

		personList.add(person);
		
		return personList;
	}
}
