package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.Category;
import edu.gatech.cs2340.donationtracker.model.DonationItem;
import edu.gatech.cs2340.donationtracker.model.GlobalVariables;
import edu.gatech.cs2340.donationtracker.model.Location;

import java.util.ArrayList;
import java.util.List;

public class SearchAll extends AppCompatActivity {

    List<Location> list = new ArrayList<>();
    List<DonationItem> dil;

    Spinner category;
    Spinner items;
    EditText itemName;
    TextView n;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_all);

        dil = new ArrayList<>();
        list = ((GlobalVariables) getApplication()).getLocations();

        for(Location l : list) {
            for(DonationItem d : l.getItems()) {
                dil.add(d);
            }
        }


        category = (Spinner) findViewById(R.id.categorySpinnerAll);
        ArrayAdapter ad1 = new ArrayAdapter<Category>(getApplicationContext(), android.R.layout.simple_spinner_item, Category.values());
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(ad1);

        items = (Spinner) findViewById(R.id.itemSpinnerAll);
        itemName = (EditText) findViewById(R.id.nameAll);

        n = (TextView) findViewById(R.id.nothin);
        n.setText("");
    }

    public void homeagain2(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void search2(View v) {
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
