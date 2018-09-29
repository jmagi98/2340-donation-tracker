package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.*;
import edu.gatech.cs2340.donationtracker.R;

public class LoginPage extends AppCompatActivity {

    private EditText _usernameEditText;
    private EditText _passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

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
        Toast.makeText(this, "You've logged in!", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
    }
}
