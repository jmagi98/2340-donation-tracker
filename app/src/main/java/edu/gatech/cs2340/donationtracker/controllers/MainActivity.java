package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import edu.gatech.cs2340.donationtracker.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void logoutButtonOnClick(View v) {
        Intent intent = new Intent(this, WelcomePage.class);
        Toast.makeText(this, "You've logged out!", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }

    public void loaddataButtonOnClick1(View v) {
        Intent i = new Intent(this, LoadData.class);
        startActivity(i);
    }
}
