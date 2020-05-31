package com.bridz.address_book.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.bridz.address_book.model.Person;
import com.bridz.address_book.service.AddressBookManager;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AddressBookController {
	
	public void start() {
		AddressBookManager addressBookManager =  new AddressBookManager();
		addressBookManager.start();
	}
	
}
