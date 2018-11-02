package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.*;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.DonationItem;
import edu.gatech.cs2340.donationtracker.model.Location;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import edu.gatech.cs2340.donationtracker.model.GlobalVariables;


public class LoadData extends AppCompatActivity {

    private List<Location> locations = new ArrayList<>();
    private Spinner s;
    List<Location> l = new ArrayList<>();

    TextView tvname;
    TextView tvlat;
    TextView tvlon;
    TextView tvstreet;
    TextView tvcity;
    TextView tvstate;
    TextView tvzip;
    TextView tvtype;
    TextView tvphone;
    TextView tvweb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ((GlobalVariables) getApplication()).setLocations(readSDFile());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_data);

        new Thread(new Runnable() {
            public void run() {
                locations = readSDFile();
            }
        }).start();


        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.i("size tag", ""+locations.size());
                setloc(locations.size());
                setContentView(R.layout.activity_show_data);
                s = (Spinner) findViewById(R.id.spinner);
                ArrayAdapter ad = new ArrayAdapter<Location>(getApplicationContext(), android.R.layout.simple_spinner_item, locations);
                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                s.setAdapter(ad);
                showData(s);

                tvname = (TextView) findViewById(R.id.tvname);
                tvlat = (TextView) findViewById(R.id.tvlat);
                tvlon = (TextView) findViewById(R.id.tvlon);
                tvstreet = (TextView) findViewById(R.id.tvstreet);
                tvcity = (TextView) findViewById(R.id.tvcity);
                tvstate = (TextView) findViewById(R.id.tvstate);
                tvzip = (TextView) findViewById(R.id.tvzip);
                tvtype = (TextView) findViewById(R.id.tvtype);
                tvphone = (TextView) findViewById(R.id.tvphone);
                tvweb = (TextView) findViewById(R.id.tvweb);


//                s = (Spinner) findViewById(R.id.spinner);

//                ArrayAdapter ad = new ArrayAdapter<Location>(getApplicationContext(), android.R.layout.simple_spinner_item, l);
//                ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//                s.setAdapter(ad);
                s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        tvname.setText(((Location)s.getSelectedItem()).get_name());
                        tvlat.setText(""+((Location)s.getSelectedItem()).get_latitude());
                        tvlon.setText(""+((Location)s.getSelectedItem()).get_longitude());
                        tvstreet.setText(((Location)s.getSelectedItem()).get_name());
                        tvcity.setText(((Location)s.getSelectedItem()).get_city());
                        tvstate.setText(((Location)s.getSelectedItem()).get_state());
                        tvzip.setText(""+((Location)s.getSelectedItem()).get_zip());
                        tvtype.setText(((Location)s.getSelectedItem()).get_type());
                        tvphone.setText(((Location)s.getSelectedItem()).get_phone_number());
                        tvweb.setText(((Location)s.getSelectedItem()).get_website());
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        showData(s);
                    }
                });

            }
        });

    }
    public void show() {
        Intent i = new Intent(this, ShowData.class);
        new Thread(new Runnable() {
            public void run() {
                setloc(locations.size());
            }
        }).start();

        i.putExtra("list", (Serializable) locations);
        startActivity(i);
    }

    public void home() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void setloc(int size) {
//        Log.i("setlock", " "+ locations.size());

        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        for(int x = 0; x < size; x++) {
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


    public void enter(View view) {

    }

    private void showData(Spinner s) {
        tvname = (TextView) findViewById(R.id.tvname);
        tvlat = (TextView) findViewById(R.id.tvlat);
        tvlon = (TextView) findViewById(R.id.tvlon);
        tvstreet = (TextView) findViewById(R.id.tvstreet);
        tvcity = (TextView) findViewById(R.id.tvcity);
        tvstate = (TextView) findViewById(R.id.tvstate);
        tvzip = (TextView) findViewById(R.id.tvzip);
        tvtype = (TextView) findViewById(R.id.tvtype);
        tvphone = (TextView) findViewById(R.id.tvphone);
        tvweb = (TextView) findViewById(R.id.tvweb);



        tvname.setText(((Location)s.getSelectedItem()).get_name());
        tvlat.setText(""+((Location)s.getSelectedItem()).get_latitude());
        tvlon.setText(""+((Location)s.getSelectedItem()).get_longitude());
        tvstreet.setText(((Location)s.getSelectedItem()).get_name());
        tvcity.setText(((Location)s.getSelectedItem()).get_city());
        tvstate.setText(((Location)s.getSelectedItem()).get_state());
        tvzip.setText(""+((Location)s.getSelectedItem()).get_zip());
        tvtype.setText(((Location)s.getSelectedItem()).get_type());
        tvphone.setText(((Location)s.getSelectedItem()).get_phone_number());
        tvweb.setText(((Location)s.getSelectedItem()).get_website());


    }

    public void back(View v) {
        Intent i = new Intent(this, WelcomePage.class);
        startActivity(i);
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
}
