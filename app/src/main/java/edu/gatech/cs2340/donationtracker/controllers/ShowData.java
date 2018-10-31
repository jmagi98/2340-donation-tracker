package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.Location;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ShowData extends AppCompatActivity {
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);
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

        l = ((List<Location>) getIntent().getExtras().getSerializable("list"));

        s = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<Location> ad = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, l);
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(ad);

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

            }
        });
    }



    public void back(View v) {
        Intent i = new Intent(this, WelcomePage.class);
        startActivity(i);
    }

    public void enter(View v) {

    }
}
