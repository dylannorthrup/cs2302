package com.example.androidaddressbook;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class AddressBookActivity extends Activity {
  private boolean editFlg = false;
  private ListView listView;
  private AddressBookList addressBook;
  private String addressBookFilename;
  private String strAddEntries;
  private String strNoEntries;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_address_book);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.address_book, menu);
    return true;
  }

}
