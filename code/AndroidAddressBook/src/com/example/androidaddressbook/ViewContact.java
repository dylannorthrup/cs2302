package com.example.androidaddressbook;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewContact extends Activity {

	private final int REQCODE = 1;
	private int rowPos; // selected row position
	
	private AddressBookList addressBook;
	private String addressBookFilename;
	private String strAddEntries;
	
	private TextView nameTextView; // displays contact's name 
	private TextView phoneTextView; // displays contact's phone
	private TextView emailTextView; // displays contact's email
	private TextView streetTextView; // displays contact's street
	private TextView cityTextView; // displays contact's city/state/zip
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_contact);

		addressBookFilename = getString(R.string.strFileName);
		strAddEntries = getString(R.string.strAddEntries);
		addressBook = new AddressBookList(getApplicationContext(),
				                      addressBookFilename,strAddEntries);
		
		// get the EditTexts
		nameTextView = (TextView) findViewById(R.id.nameTextView);
		phoneTextView = (TextView) findViewById(R.id.phoneTextView);
		emailTextView = (TextView) findViewById(R.id.emailTextView);
		streetTextView = (TextView) findViewById(R.id.streetTextView);
		cityTextView = (TextView) findViewById(R.id.cityTextView);
	      
		// get the selected contact's row ID
		Bundle extras = getIntent().getExtras();
		rowPos = extras.getInt("row_pos");
		nameTextView.setText(addressBook.getName(rowPos));
		phoneTextView.setText(addressBook.getPhone(rowPos));
		emailTextView.setText(addressBook.getEmail(rowPos));
		streetTextView.setText(addressBook.getStreet(rowPos));
		cityTextView.setText(addressBook.getCityStateZip(rowPos));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.view_contact_menu, menu);
		return true;
	}

	// handle choice from options menu
	@Override
	public boolean onOptionsItemSelected(MenuItem item)  {
		switch (item.getItemId()) {
			case R.id.editItem:
				// create an Intent to launch the AddEditContact Activity
	            Intent addEditContact =
	            		new Intent(this, AddEditContact.class);
    			// pass the selected contact's row position as an extra
	            addEditContact.putExtra("row_pos", rowPos);
	            // start the Activity
	            startActivityForResult(addEditContact,REQCODE);
	            return true;
			case R.id.deleteItem:
	            deleteContact(); // delete the displayed contact
	            return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onActivityResult(int reqCode, int resCode, Intent data) {
		if (reqCode == REQCODE)
			if (resCode == RESULT_OK) {
				nameTextView.setText(data.getStringExtra("name"));
				phoneTextView.setText(data.getStringExtra("phone"));
				emailTextView.setText(data.getStringExtra("email"));
				streetTextView.setText(data.getStringExtra("street"));
				cityTextView.setText(data.getStringExtra("city"));
			}
	}

	// delete a contact
	private void deleteContact() {
		// create a new AlertDialog Builder
		AlertDialog.Builder builder =
				new AlertDialog.Builder(ViewContact.this);

		builder.setTitle(R.string.confirmTitle); // title bar string
		builder.setMessage(R.string.confirmMessage); // message to display

		// provide an OK button that simply dismisses the dialog
		builder.setPositiveButton(R.string.button_delete,
			new DialogInterface.OnClickListener() {
				@Override
	            public void onClick(DialogInterface dialog, int button) {
					addressBook.removeEntry(rowPos);
					finish();
	            }
			});
	      
	      builder.setNegativeButton(R.string.button_cancel, null);
	      builder.show(); // display the Dialog
	}
}