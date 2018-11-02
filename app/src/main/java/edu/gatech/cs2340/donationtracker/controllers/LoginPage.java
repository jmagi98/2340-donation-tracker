package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.*;
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


public class LoginPage extends AppCompatActivity {

    private EditText _usernameEditText;
    private EditText _passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        ((GlobalVariables) getApplication()).loadData();

        if( ((GlobalVariables) getApplication()).getLocations() == null || ((GlobalVariables) getApplication()).getLocations().size() < 1) {
            ((GlobalVariables) getApplication()).setLocations(readSDFile());
        }

        _usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        _passwordEditText = (EditText) findViewById(R.id.passwordEditText);
    }

    public void loginButtonOnClick(View v) {
        final String _username = _usernameEditText.getText().toString();
        final String _password = _passwordEditText.getText().toString();

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference();

        dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                boolean wrong = true;
                for (DataSnapshot ds : dataSnapshot.child("users").getChildren()) {
                    if (ds.child("email").getValue().equals(_username)
                            && ds.child("password").getValue().equals(_password)) {
                        changeToMainActivity();
                        wrong = false;
                        break;
                    }
                }
                if (wrong) {
                    wrongLogin();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    public void cancelButtonOnClick(View v) {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
    }

    public void wrongLogin() {
        Toast.makeText(this, "Incorrect Username/password", Toast.LENGTH_LONG).show();
    }
    public void changeToMainActivity() {
        Toast.makeText(this, "You've logged in!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
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
