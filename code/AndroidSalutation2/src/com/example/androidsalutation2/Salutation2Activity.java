package com.example.androidsalutation2;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

//******************************************************************
//The AdroidSalutation App Activity.
//******************************************************************

import android.view.View;
import android.widget.*;

public class Salutation2Activity extends Activity {

  private Button btnSalutation;
  private TextView header;
  private EditText firstNameBx;
  private EditText lastNameBx;
  private RadioGroup genderBtns;
  private RadioButton femaleRadBtn;
  private RadioButton maleRadBtn;
  private String helloMs;
  private String helloMr;
  private String noName;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_salutation2);

    header = (TextView) findViewById(R.id.headerTxt);
    firstNameBx = (EditText) findViewById(R.id.firstNameTxtBx);
    lastNameBx = (EditText) findViewById(R.id.lastNameTxtBx);
    genderBtns = (RadioGroup) findViewById(R.id.radioGroup1);
    femaleRadBtn = (RadioButton) findViewById(R.id.femaleRadBtn);
    maleRadBtn = (RadioButton) findViewById(R.id.maleRadBtn);
    helloMs = (String) getString(R.string.helloMs);
    helloMr = (String) getString(R.string.helloMr);
    noName = (String) getString(R.string.noName);

    btnSalutation = (Button) findViewById(R.id.salutationBtn);
    btnSalutation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String fullname = firstNameBx.getText() + " " +
            lastNameBx.getText();

        int selectedID = genderBtns.getCheckedRadioButtonId();
        RadioButton gender = (RadioButton) findViewById(selectedID);

        if (fullname.contentEquals(" ")){
          header.setText(noName);
        } else {
          if (gender == femaleRadBtn)
            header.setText(helloMs + " " + fullname);
          else if (gender == maleRadBtn)
            header.setText(helloMr + " " + fullname);
        }
      }
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.salutation2, menu);
    return true;
  }

}
