package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.Category;
import edu.gatech.cs2340.donationtracker.model.DonationItem;
import edu.gatech.cs2340.donationtracker.model.GlobalVariables;
import edu.gatech.cs2340.donationtracker.model.Location;
import edu.gatech.cs2340.donationtracker.controllers.LoadData;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataEntry extends AppCompatActivity {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    private List<Location> ls = new ArrayList<>();
    Spinner location;
    EditText sd;
    EditText ld;
    EditText value;
    EditText category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (((GlobalVariables) getApplication()).getLocations() == null) {
            ((GlobalVariables) getApplication()).setLocations(readSDFile());
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_entry);
        List<Location> locations = ((GlobalVariables) getApplication()).getLocations();
        ls = new ArrayList<>();
        for(Location locals: locations) {
            ls.add(locals);
        }
        sd = (EditText) findViewById(R.id.shortDescriptionText);
        ld = (EditText) findViewById(R.id.longDescriptionText);
        value = (EditText) findViewById(R.id.valueEditText);
        category = (EditText) findViewById(R.id.categoryEditText);


        location = (Spinner) findViewById(R.id.locationSpinner);
        ArrayAdapter ad = new ArrayAdapter<Location>(getApplicationContext(), android.R.layout.simple_spinner_item, ls);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        location.setAdapter(ad);

    }

    public void createOnClick(View v) {
        DonationItem temp = new DonationItem(new Timestamp(System.currentTimeMillis()),((Location) location.getSelectedItem()), ""+sd.getText(), ""+ ld.getText(), ""+value.getText(), ""+category.getText());
        ArrayList<Location> newLocalList = new ArrayList<>();
        for(Location l : ls) {
            newLocalList.add(l);
            if(l.get_name().equals(((Location) location.getSelectedItem()).get_name())) {
                Log.i("tester", temp._shortDescription);
                l.addItem(temp);
                Log.i("size tag", "" + l.getItems().size());
            }
        }
        ((GlobalVariables) getApplication()).setLocations(newLocalList);
    }

    public List<Location> readSDFile() {
        System.out.print("called");

        List<Location> l = new ArrayList<>();

        try {
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                Log.d("tag", "readSDFile: called ");

                String[] tokens = line.split(",");
                ArrayList<DonationItem> nullArrayList = new ArrayList<>();
                l.add(new Location(tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], tokens[6], Integer.parseInt(tokens[7]), tokens[8], tokens[9], tokens[10]));
            }
            br.close();
        } catch (IOException e) {
            System.out.print("here");

        }
        Log.i("tag", ""+l.size());
        return l;
    }


    public void goBack(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
