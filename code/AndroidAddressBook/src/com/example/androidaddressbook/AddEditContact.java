// AddEditContact.java
package com.example.androidaddressbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AddEditContact extends Activity 
{
	private int rowPos; // row number of contact being edited, if any

	private AddressBookList addressBook;
	private String addressBookFilename;
	private String strAddEntries;

	private Bundle extras;
	
	// EditTexts for contact information
	private EditText nameEditText;
	private EditText phoneEditText;
	private EditText emailEditText;
	private EditText streetEditText;
	private EditText cityEditText;
   
	// called when the Activity is first started
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); // call super's onCreate
		setContentView(R.layout.activity_add_edit_contact); // inflate the UI

		addressBookFilename = getString(R.string.strFileName);
		strAddEntries = getString(R.string.strAddEntries);
		addressBook = new AddressBookList(getApplicationContext(),
				                      addressBookFilename,strAddEntries);

		nameEditText = (EditText) findViewById(R.id.nameEditText);
		phoneEditText = (EditText) findViewById(R.id.phoneEditText);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		streetEditText = (EditText) findViewById(R.id.streetEditText);
		cityEditText = (EditText) findViewById(R.id.cityEditText);
      
		extras = getIntent().getExtras(); // get Bundle of extras

		// if there are extras, use them to populate the EditTexts
		if (extras != null) {
			rowPos = extras.getInt("row_pos");
			nameEditText.setText(addressBook.getName(rowPos));
			phoneEditText.setText(addressBook.getPhone(rowPos));
			emailEditText.setText(addressBook.getEmail(rowPos));
			streetEditText.setText(addressBook.getStreet(rowPos));
			cityEditText.setText(addressBook.getCityStateZip(rowPos));
		}
      
		// set event listener for the Save Contact Button
		Button saveContactButton = 
					(Button) findViewById(R.id.saveContactButton);
		saveContactButton.setOnClickListener(saveContactButtonClicked);
	}
	
	public void saveData(String name, String phone, String email,
			               String street, String city) {
		if (extras != null) {
			addressBook.replaceEntry(rowPos,name,phone,email,street,city);
			Intent data = new Intent();
			data.putExtra("name",name);
			data.putExtra("phone",phone);
			data.putExtra("email",email);
			data.putExtra("street",street);
			data.putExtra("city",city);
			setResult(RESULT_OK,data);
		} else
			addressBook.saveEntry(name,phone,email,street,city);
	}

	// responds to event generated when user clicks the Done Button
	OnClickListener saveContactButtonClicked = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (nameEditText.getText().length() != 0) {
				saveData(nameEditText.getText().toString(),
							phoneEditText.getText().toString(),
							emailEditText.getText().toString(),
							streetEditText.getText().toString(),
							cityEditText.getText().toString());
				finish();   // return to the previous Activity
			} else {
				// create a new AlertDialog Builder
				AlertDialog.Builder builder = 
						new AlertDialog.Builder(AddEditContact.this);
      
				// set dialog title & message
				builder.setTitle(R.string.errorTitle); 
				builder.setMessage(R.string.errorMessage);
				
				// set the ok button
				builder.setPositiveButton(R.string.button_ok, null);

				// set the cancel button
				builder.setNegativeButton(R.string.button_cancel,
				    new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							finish();   // return to the previous Activity
						}
					});
				builder.show(); // display the Dialog
			}
		}
	};
} 