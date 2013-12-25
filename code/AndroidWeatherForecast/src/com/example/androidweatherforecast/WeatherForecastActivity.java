package com.example.androidweatherforecast;

/*
 * Course: CS 2302
 * Section: 2
 * Name: Dylan Northrup
 * Professor: Shaw
 * Assignment #: Homework 10
 */

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.Menu;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import weather.Weather;

public class WeatherForecastActivity extends Activity {

  private TextView txtHeading;          // The heading
  private TextView txtForeCast;         // The forecast text
  private Button infoBtn;               // The more/less toggle button
  private Button zipCodeBtn;            // Button to call up zip code dialog
  private LinearLayout weatherView;     // The view layout
  private EditText txtZipCode;

  public static final String PREFS_NAME = "ForecastPrefsFile";
  private String zipCodeStr = "";  			// The zipcode string
  private String defaultZipCode = "30303";  // The default zipcode (if they haven't chosen a different one)

  // Strings to load
  private String zipCodeErrStr = "";
  private String searchingStr = "";
  private String lessInfoStr = "";
  private String moreInfoStr = "";
  private String forecastStr = "";
  private String findStr = "";

  private boolean allInfo = true;       // Flags more/less state

  private Weather weather;              // The weather library object
  private AlertDialog alertDialog;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_forecast);

    // Restore preferences
    SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
    String zipPref = settings.getString("zipCode", defaultZipCode);
    zipCodeStr = zipPref;
    
    // The weather view layout
    weatherView = (LinearLayout) findViewById(R.id.weatherView);

    // The heading text view
    txtHeading = (TextView) findViewById(R.id.textviewHead);
    txtHeading.setPaintFlags(txtHeading.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

    // The text view where the forecast goes
    txtForeCast = (TextView) findViewById(R.id.textviewForecast);

    // Loading the Strings to use
    searchingStr = getString(R.string.searching);
    lessInfoStr = getString(R.string.lessinfo);
    moreInfoStr = getString(R.string.moreinfo);
    findStr = getString(R.string.stringtofind);
    zipCodeErrStr = getString(R.string.zipcodeerror);

    // The handler for the Zip Code button
    zipCodeBtn = (Button) findViewById(R.id.zipCodeButton);
    zipCodeBtn.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        getZipCode();
      }
    });

    // The handler for the more/less info button
    infoBtn = (Button) findViewById(R.id.infoButton);
    infoBtn.setOnClickListener(new View.OnClickListener() {
      public void onClick(View v) {
        allInfo = !allInfo;  // toggle the state
        if (allInfo)  // show all information
          txtForeCast.setText("Forecast for " + zipCodeStr + "\n" + forecastStr + "\n");
        else if (findStr.length() > 0) {  // show less
          int pos = forecastStr.indexOf(findStr);
          if (pos > 0) {
            txtForeCast.setText("Forecast for " + zipCodeStr + "\n" + forecastStr.substring(0,pos) + "\n");
          }
        }

        // Toggle the text on the button
        infoBtn.setText((allInfo) ? lessInfoStr : moreInfoStr);

        // Update the view
        weatherView.invalidate();
      }
    });


    
    // Begin the program by calling GetWeather() for the zipcode
    txtForeCast.setText("\n" + searchingStr + "\n");
    new GetWeather().execute();
  }

  // This asynchronous task calls the Weather library which accesses the
  //   internet.  When the call is finished the onPostExecute method is
  //   called with the result.
  private class GetWeather extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... params) {
      weather = new Weather(zipCodeStr);    // start the session
      return weather.getForcast();          // return result
    }

    @Override
    protected void onPostExecute(String result) {
      if (result == null || result.equals("")) {
        // Show error message for null result
        Toast.makeText(getApplicationContext(),
            zipCodeErrStr,Toast.LENGTH_LONG).show();
        txtForeCast.setText("Forecast for " + zipCodeStr + "\n" + zipCodeErrStr + "\n");
      } else {
        // Load the result as the forecastStr
        forecastStr = result;
        txtForeCast.setText("Forecast for " + zipCodeStr + "\n" + forecastStr + "\n");
      }
    }
  }

  // Creates a dialog box that gets the zipCode
  public void getZipCode() {
    // inflate layout in layout folder called "zipcodedlg"
    LayoutInflater li = LayoutInflater.from(this);
    View zipcodedlgView = li.inflate(R.layout.zipcodedlg, null);

    // instantiate the AlertDialog
    AlertDialog.Builder alertDialogBuilder =
        new AlertDialog.Builder(this);
    alertDialogBuilder.setView(zipcodedlgView).
    setCancelable(false);

    // set the editText field with the previous zipCodeStr used
    txtZipCode = (EditText) zipcodedlgView.findViewById(R.id.editTextDialogZipCode);
    txtZipCode.setText(zipCodeStr);

    // create the OK Button
    Button btnOK = (Button) zipcodedlgView.
        findViewById(R.id.okButton);
    btnOK.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        String zipStr = txtZipCode.getText().toString().
            trim();
        if (zipStr.length() == 0) {  // if empty, show error
          Toast.makeText(getApplicationContext(),
              getString(R.string.zipcodereqtext),
              Toast.LENGTH_LONG).show();
        } else  {  // if not empty, save it, store it as the new ZipCode preference and end dialog
          zipCodeStr = zipStr;
          alertDialog.dismiss();
          SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
          SharedPreferences.Editor ed = settings.edit();
          ed.putString("zipCode", zipStr);
          ed.commit();
        }
        // Add in lines to update forecast here. We do it here because 
        // this is called when we click 'Ok'
        txtForeCast.setText("\n" + searchingStr + "\n");
        new GetWeather().execute();
      }
    });
    // create the Cancel Button
    Button btnCancel = (Button)
              zipcodedlgView.findViewById(R.id.cancelButton);
    btnCancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            alertDialog.dismiss();
        }
    });

    // create and then show the dialog
    alertDialog = alertDialogBuilder.create();
    alertDialog.show();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.weather_forecast, menu);
    return true;
  }
}
