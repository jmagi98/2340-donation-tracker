package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import com.google.firebase.database.*;
import com.google.gson.Gson;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.Location;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class LoadData extends AppCompatActivity {

    private List<Location> locations;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);
        /*
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setloc();
            }
        });
        */
    }

    public void show(View v) {
        Intent i = new Intent(this, ShowData.class);
        String temp = new Gson().toJson(locations);
        i.putExtra("list", temp);
        startActivity(i);
    }

    public void home(View v) {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    /*

    public void setloc() {

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        for(int x = 0; x < locations.size(); x++) {
            String temp = locations.get(x).get_name();
            db.child("locations").child(""+x).setValue(temp);
            db.child("locations").child(""+x).child("name").setValue(temp);
            db.child("locations").child(""+x).child("latitude").setValue(""+locations.get(x).get_latitude());
            db.child("locations").child(""+x).child("longitude").setValue(""+locations.get(x).get_longitude());
            db.child("locations").child(""+x).child("street_address").setValue(locations.get(x).get_streetAddress());
            db.child("locations").child(""+x).child("city").setValue(locations.get(x).get_city());
            db.child("locations").child(""+x).child("state").setValue(locations.get(x).get_state());
            db.child("locations").child(""+x).child("zip").setValue(""+locations.get(x).get_zip());
            db.child("locations").child(""+x).child("type").setValue(locations.get(x).get_type());
            db.child("locations").child(""+x).child("phone").setValue(locations.get(x).get_phone_number());
            db.child("locations").child(""+x).child("website").setValue(locations.get(x).get_website());
        }
    }
    */

    public void loaddataButtonOnClick(View view) {
    }

    private List<Location> readSDFile() {
        List<Location> l = new ArrayList<>();

        try {
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            BufferedReader br = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                Log.d("token", tokens[1]);
                l.add(new Location(tokens[1], Double.parseDouble(tokens[2]), Double.parseDouble(tokens[3]), tokens[4], tokens[5], tokens[6], Integer.parseInt(tokens[7]), tokens[8], tokens[9], tokens[10]));

            }

        } catch (IOException e) {

        }
        return l;
    }
}
