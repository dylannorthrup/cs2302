package com.example.androidaddressbook;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;

import android.content.Context;

public class AddressBookList {
	private ArrayList<AddressBookEntry> addressBookList;
	private Context appContext;
	private String filename;
	private String addMessage;

	// The AddressBookList Constructor
	public AddressBookList(Context appContext, String filename,
			               String addMessage) {
		addressBookList = new ArrayList<AddressBookEntry>();
		this.appContext = appContext;
		this.filename = filename;
		this.addMessage = addMessage;
		loadAddressBook();
	}

	// Accessor for indexed name
	public String getName(int index) {
		return addressBookList.get(index).getName();
	}

	// Accessor for indexed phone
	public String getPhone(int index) {
		return addressBookList.get(index).getPhone();
	}

	// Accessor for indexed email
	public String getEmail(int index) {
		return addressBookList.get(index).getEmail();
	}

	// Accessor for indexed street
	public String getStreet(int index) {
		return addressBookList.get(index).getStreet();
	}

	// Accessor for indexed cityStateZip
	public String getCityStateZip(int index) {
		return addressBookList.get(index).getCityStateZip();
	}

	// Removes a given entry
	public void removeEntry(int index) {
		addressBookList.remove(index);
		saveAllEntries();
	}
    
	// Loads the Address Book from file
    public boolean loadAddressBook() {
    	boolean success = true;
        String name, phone, email, street, cityStateZip;

        addressBookList.clear();
    	try {
        	FileInputStream fis = appContext.openFileInput(filename);
            DataInputStream in = new DataInputStream(fis);
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            name = br.readLine();
            while (name != null) {
            	phone = br.readLine();
            	email = br.readLine();
            	street = br.readLine();
            	cityStateZip = br.readLine();
            	addressBookList.add(new AddressBookEntry(name, phone,
                		                email, street, cityStateZip));
            	name = br.readLine();
            }
            br.close();
    	} catch (Exception ex) {
    		success = false;
    	}
    	Collections.sort(addressBookList);
    	return success;
    }

	// Gets the nameArray
    public String [] getNameArray() {
    	String [] strArray;
        if (addressBookList.size() == 0) {
        	strArray = new String[1];
        	strArray[0] = addMessage;
        } else {
        	strArray = new String[addressBookList.size()];
        	for (int i = 0; i < addressBookList.size(); ++i)
        		strArray[i] = addressBookList.get(i).getName();
        }
        return strArray;
    }
    
	// Saves an entry, appending it to the past entries
    public boolean saveEntry(String name, String phone, String email,
    		                     String street, String cityStateZip) {
    	boolean success = true;
    	
    	name = (name == null) ? "" : name;
    	phone = (phone == null) ? "" : phone;
    	email = (email == null) ? "" : email;
    	street = (street == null) ? "" : street;
    	cityStateZip = (cityStateZip == null) ? "" : cityStateZip;
    	try {
        	FileOutputStream fos = appContext.openFileOutput(filename,
        			                              Context.MODE_APPEND);
        	OutputStreamWriter out = new OutputStreamWriter(fos);
        	out.write(name + "\n");
        	out.write(phone + "\n");
        	out.write(email + "\n");
        	out.write(street + "\n");
        	out.write(cityStateZip + "\n");
        	out.close();
        	loadAddressBook();
    	} catch (Exception ex) {
    		success = false;
    	}
    	return success;
    }
    
	// Replaces a given entry
    public boolean replaceEntry(int index, String name, String phone,
                       String email, String street, String cityStateZip) {
    	addressBookList.remove(index);
		addressBookList.add(new AddressBookEntry(name,phone,email,
													street,cityStateZip));
    	Collections.sort(addressBookList);
    	return saveAllEntries();
    }
    
	// Save's all entries, replacing the old entries
    public boolean saveAllEntries() {
    	boolean success = true;
    	
    	try {
        	FileOutputStream fos = appContext.openFileOutput(filename,
        			                             Context.MODE_PRIVATE);
        	OutputStreamWriter out = new OutputStreamWriter(fos);
        	for (AddressBookEntry entry : addressBookList) {
            	out.write(entry.getName() + "\n");
            	out.write(entry.getPhone() + "\n");
            	out.write(entry.getEmail() + "\n");
            	out.write(entry.getStreet() + "\n");
            	out.write(entry.getCityStateZip() + "\n");
        	}
        	out.close();
    	} catch (Exception ex) {
    		success = false;
    	}
    	return success;
    }
}