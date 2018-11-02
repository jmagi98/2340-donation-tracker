package edu.gatech.cs2340.donationtracker.controllers;

import android.content.ClipData;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.DonationItem;
import edu.gatech.cs2340.donationtracker.model.GlobalVariables;
import edu.gatech.cs2340.donationtracker.model.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class ShowItems extends AppCompatActivity {

    List<Location> ls = new ArrayList<>();
    List<DonationItem> dil;
    Spinner locations;
    Spinner items;
    TextView sd;
    TextView ld;
    TextView value;
    TextView category;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_items);
        if (((GlobalVariables) getApplication()).getLocations() == null) {
            ((GlobalVariables) getApplication()).setLocations(readSDFile());
        }


//        Log.i("here", "here");

        locations = (Spinner) findViewById(R.id.locationSpinner);
        items = (Spinner) findViewById(R.id.itemSpinner);
        sd = (TextView) findViewById(R.id.shortDescription);
        ld = (TextView) findViewById(R.id.longDescription);
        value = (TextView) findViewById(R.id.value);
        category = (TextView) findViewById(R.id.category);

        List<Location> locals = ((GlobalVariables) getApplication()).getLocations();
        ls = new ArrayList<>();
        for(Location local: locals) {
            ls.add(local);
        }

        ArrayAdapter ad = new ArrayAdapter<Location>(getApplicationContext(), android.R.layout.simple_spinner_item, locals);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locations.setAdapter(ad);

        locations.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("here", "listener called");
                dil = ((Location)locations.getSelectedItem()).getItems();
//                Log.i("length", "" + dil.size());
                List<DonationItem> theItems = new ArrayList<>();
                for(DonationItem locals: dil) {
                    theItems.add(locals);
                    Log.i("tag", locals._shortDescription);
                }
                ArrayAdapter temp = new ArrayAdapter<DonationItem>(getApplicationContext(), android.R.layout.simple_spinner_item, dil);
                temp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                items.setAdapter(temp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        items.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sd.setText(((DonationItem) items.getSelectedItem())._shortDescription);
                ld.setText(((DonationItem) items.getSelectedItem())._fullDescription);
                value.setText(((DonationItem) items.getSelectedItem())._value);
                category.setText(((DonationItem) items.getSelectedItem())._category);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
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


    public void goBackHome(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}
