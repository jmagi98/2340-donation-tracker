package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.DonationItem;
import edu.gatech.cs2340.donationtracker.model.GlobalVariables;
import edu.gatech.cs2340.donationtracker.model.Location;

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

    public void enterItemButtonClick(View v) {
        Intent i = new Intent(this, DataEntry.class);
        Toast.makeText(this, "Item has been added", Toast.LENGTH_LONG).show();
        startActivity(i);
    }

    public void showItems(View v) {
        Intent i = new Intent(this, ShowItems.class);
        startActivity(i);
    }
}
