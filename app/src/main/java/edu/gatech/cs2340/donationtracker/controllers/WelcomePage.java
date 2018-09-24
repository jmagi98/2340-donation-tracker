package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import edu.gatech.cs2340.donationtracker.R;

public class WelcomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_page);
    }

    public void loginButtonOnClick(View v) {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }

    public void signupButtonOnClick(View v) {
        Intent intent = new Intent(this, SignupPage.class);
        startActivity(intent);
    }

}
