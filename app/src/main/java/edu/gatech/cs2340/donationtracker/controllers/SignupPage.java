package edu.gatech.cs2340.donationtracker.controllers;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.model.*;

public class SignupPage extends AppCompatActivity {

    private EditText _usernameEditText;
    private EditText _passwordEditText;
    private EditText _e_mailEditText;
    private Spinner _accessLevelSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_page);

        _usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        _passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        _e_mailEditText = (EditText) findViewById(R.id.e_mailEditText);
        _accessLevelSpinner = (Spinner) findViewById(R.id.accessLevelSpinner);

        ArrayAdapter<AccessLevel> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, AccessLevel.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        _accessLevelSpinner.setAdapter(adapter);
    }

    public void signupButtonOnClick(View v) {
        String _username = _usernameEditText.getText().toString();
        String _password = _passwordEditText.getText().toString();
        String _e_mail = _e_mailEditText.getText().toString();
        AccessLevel _accessLevel = (AccessLevel) _accessLevelSpinner.getSelectedItem();

        if (_username.length() > 0
                && !_username.contains(" ")
                && _password.length() > 0
                && !_password.contains(" ")
                && _e_mail.length() > 0
                && !_e_mail.contains(" ")
                && _e_mail.contains("@")
                && _e_mail.contains(".")
                && _e_mail.lastIndexOf(".") > _e_mail.indexOf('@')) {
            User temp = new User(_username, _password, _e_mail, _accessLevel);
            temp.writeNewUserToDatabase();

            Toast.makeText(this, "You have successfully signed up! Yay!", Toast.LENGTH_LONG).show();
            startActivity(new Intent(this, WelcomePage.class));
        } else {
            Toast.makeText(this, "Sorry, you haven't been signed up!\nusername, password, or e-mail are entered incorrectly", Toast.LENGTH_LONG).show();
        }
    }

    public void cancelButtonOnClick(View v) {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
    }
}
