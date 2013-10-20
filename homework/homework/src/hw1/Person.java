package hw1;

/*
 * Course: CS 2302
 * Section: 02
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: homework 1
 */

public class Person {
	private String name;
	private String address;
	private String phoneNumber;
	private String emailAddress;
	
	// no-arg constructor to create Person with default values
	public Person() {
		this("Default Name", "Default Address", "Default phone", "Default email");
	}
	
	// Constructor to pass args in
	public Person(String name, String address, String phone, String email) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phone;
		this.emailAddress = email;
	}
	
	// Print out details about Person
	public String toString() {
	  return  "Person Name: " + this.name + 
	          "\nPerson Address: " + this.address + 
	          "\nPerson Phone Number: " + this.phoneNumber + 
	          "\nPerson Email Address: " + this.emailAddress + 
	          "\n";
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return the emailAddress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	/**
	 * @param emailAddress the emailAddress to set
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

}
