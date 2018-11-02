package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.Category;
import edu.gatech.cs2340.donationtracker.model.DonationItem;
import edu.gatech.cs2340.donationtracker.model.GlobalVariables;
import edu.gatech.cs2340.donationtracker.model.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Search extends AppCompatActivity {

    List<Location> list = new ArrayList<>();
    List<DonationItem> dil;
    TextView n;
    Spinner location;
    Spinner category;
    Spinner items;
    EditText itemName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        list = ((GlobalVariables) getApplication()).getLocations();

        location = (Spinner) findViewById(R.id.locationSpinner);
        ArrayAdapter ad = new ArrayAdapter<Location>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(ad);

        location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                dil = ((Location)location.getSelectedItem()).getItems();
                /*
                ArrayAdapter temp = new ArrayAdapter<DonationItem>(getApplicationContext(), android.R.layout.simple_spinner_item, dil);
                temp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                items.setAdapter(temp);
                */
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        category = (Spinner) findViewById(R.id.categorySpinner);
        ArrayAdapter ad1 = new ArrayAdapter<Category>(getApplicationContext(), android.R.layout.simple_spinner_item, Category.values());
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(ad1);

        items = (Spinner) findViewById(R.id.itemSpinner);
        itemName = (EditText) findViewById(R.id.itemNameEditText);

        n = (TextView) findViewById(R.id.nothing);
        n.setText("");




    }

    public void homeagain(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void search(View v) {
        List<DonationItem> temp = new ArrayList<>();
        if(itemName.getText().length() > 0) {
            for(DonationItem d : dil) {
                if(d._shortDescription.contains(itemName.getText())) {
                    temp.add(d);
                }
            }
        } else {
            for(DonationItem d : dil) {
                if(d._category.toString().equals(((Category)category.getSelectedItem()).toString())) {
                    temp.add(d);
                }
            }
        }

        ArrayAdapter ad3 = new ArrayAdapter<DonationItem>(getApplicationContext(), android.R.layout.simple_spinner_item, temp);
        ad3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        items.setAdapter(ad3);

        if(temp.size() == 0) {
            n.setText("Item doesn't exist");
        }
    }
}
