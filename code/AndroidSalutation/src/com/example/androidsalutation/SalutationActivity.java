package com.example.androidsalutation;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.*;

public class SalutationActivity extends Activity {
  private Button btnSalutation;
  private TextView header;
  private EditText firstNameBx;
  private EditText lastNameBx;
  private RadioGroup genderButtons;
  private RadioButton maleRadBtn;
  private RadioButton femaleRadBtn;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_salutation);
    
    header = (TextView) findViewById(R.id.headerTxt);
    firstNameBx = (EditText) findViewById(R.id.firstNameTxtBx);
    lastNameBx = (EditText) findViewById(R.id.lastNameTxtBx);
    genderButtons = (RadioGroup) findViewById(R.id.radioGroup1);
    femaleRadBtn = (RadioButton) findViewById(R.id.femaleRadBtn);
    maleRadBtn = (RadioButton) findViewById(R.id.maleRadBtn);
    
    btnSalutation = (Button) findViewById(R.id.salutationBtn);
    btnSalutation.setOnClickListener(new View.OnClickListener() {
      
      @Override
      public void onClick(View v) {
        String fullname = firstNameBx.getText() + " " + lastNameBx.getText();
        int selectedId = genderButtons.getCheckedRadioButtonId();
        Button gender = (Button) findViewById(selectedId);

        if (gender == femaleRadBtn) {
          header.setText("Hello Ms. " + fullname);
        } else {
          header.setText("Hello Mr. " + fullname);
        }
      };
    });
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.salutation, menu);
    return true;
  }

}
