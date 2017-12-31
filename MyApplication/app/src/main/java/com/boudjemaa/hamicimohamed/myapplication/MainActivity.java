package com.boudjemaa.hamicimohamed.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPref = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(!sharedPref.getString(getString(R.string.user_name), "").trim().equals("")) {
            // Create an intent for the second activity
            Intent i = new Intent(this, SecondActivity.class);

            // Kill this activity
            finish();

            // Start the activity
            startActivity(i);
        }
    }
    //we submit the user parameters
    public void submit(View view) {
        // Create an intent for the second activity
        Intent intent = new Intent(this, SecondActivity.class);

        // Get the name and email values
        EditText eTname = findViewById(R.id.saved_name);
        EditText eTemail = findViewById(R.id.email);
        EditText eTheight = findViewById(R.id.height);
        EditText eTweight = findViewById(R.id.weight);
        EditText eTage = findViewById(R.id.age);

        // Extract the user information
        String name = eTname.getText().toString();
        String email = eTemail.getText().toString();
        String height = eTheight.getText().toString();
        String weight = eTweight.getText().toString();
        String age = eTage.getText().toString();

        if(name.trim().equals("")) {
            eTname.setError( "Name is required" );
            eTname.setHint("Please enter your name");
        } else if(email.trim().equals("")) {
            eTemail.setError( "Email is required" );
            eTemail.setHint("Please enter your email");
        } else if(height.trim().equals("")) {
            eTemail.setError( "Height is required" );
            eTemail.setHint("Please enter your email");
        } else if(weight.trim().equals("")) {
            eTemail.setError( "Weight is required" );
            eTemail.setHint("Please enter your email");
        } else if(age.trim().equals("")) {
            eTemail.setError( "Age is required" );
            eTemail.setHint("Please enter your email");
        }else {
            // Save user information
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString(getString(R.string.user_name), name);
            editor.putString(getString(R.string.user_email), email);
            editor.putString(getString(R.string.height), height);
            editor.putString(getString(R.string.weight), weight);
            editor.putString(getString(R.string.age), age);
            editor.commit();

            // Start the activity
            startActivity(intent);
        }

    }
}
