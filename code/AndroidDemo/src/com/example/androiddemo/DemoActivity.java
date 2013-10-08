package com.example.androiddemo;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;

public class DemoActivity extends Activity {
  FontDemoView fontDemo;
  AlertDemoView alertDemoView;
  
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_demo);
    
    // Demo of various font manipulations
//    fontDemo = new FontDemoView(this);
//    setContentView(fontDemo);
    
    // Demo of Alerts
    alertDemoView = new AlertDemoView(this);
    setContentView(alertDemoView);
    this.runAlert();  // Need to run this in order to set up AlertDialog (defined below)
  }
  
  public void runAlert()
  {
     AlertDialog.Builder qBuilder = new AlertDialog.Builder(this);
     qBuilder.setTitle("This is the Alert Title");
     qBuilder.setMessage("This is the Alert Message");
     qBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
           alertDemoView.setMessage("You pressed: 'Yes'");
           runAlert();
        }
     });
     qBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
            alertDemoView.setMessage("You pressed: 'No'");
            runAlert();
        }
     });
     qBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int id) {
           alertDemoView.noAlert();
           alertDemoView.setMessage("The Alert is Finished!");
        }
     });
     AlertDialog alertDialog = qBuilder.create();
     alertDialog.setCancelable(false);
     alertDialog.setCanceledOnTouchOutside(false);
     alertDialog.show();
  }


  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.demo, menu);
    return true;
  }

}
