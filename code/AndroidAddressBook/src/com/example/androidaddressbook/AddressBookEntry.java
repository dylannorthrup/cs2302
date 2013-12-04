package com.example.androidaddressbook;

public class AddressBookEntry implements Comparable<AddressBookEntry>
{
	private String name, phone, email, street, cityStateZip;

	// The constructor
	public AddressBookEntry(String name, String phone, String email, 
								String street, String cityStateZip) {
		this.name = (name == null) ? "" : name;
		this.phone = (phone == null) ? "" : phone;
		this.email = (email == null) ? "" : email;
		this.street = (street == null) ? "" : street;
		this.cityStateZip = (cityStateZip == null) ? "" : cityStateZip;
	}

	// Accessor for name
	public String getName() {
		return name;
	}

	// Accessor for phone
	public String getPhone() {
		return phone;
	}

	// Accessor for email
	public String getEmail() {
		return email;
	}

	// Accessor for street
	public String getStreet() {
		return street;
	}

	// Accessor for cityStateZip
	public String getCityStateZip() {
		return cityStateZip;
	}

	// The toString that shows an address as a string
	public String toString() {
		return "\t" + getName() + "\n\t" + getPhone() + "\n\t" +
					getEmail() + "\n\t" + getStreet() + "\n\t" +
					getCityStateZip();
	}

	// Compares last names first, and secondarily first names
	public int compareTo(AddressBookEntry other)
	{
		int result = name.compareTo(other.name);
		if (result == 0)
			result = phone.compareTo(other.phone);
		if (result == 0)
			result = email.compareTo(other.email);
		if (result == 0)
			result = street.compareTo(other.street);
		if (result == 0)
			result = cityStateZip.compareTo(other.cityStateZip);
       return result;
	}
}