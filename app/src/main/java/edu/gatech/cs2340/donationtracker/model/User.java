package edu.gatech.cs2340.donationtracker.model;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {

    private String _username;
    private String _password;
    private String _e_mail;
    private AccessLevel _accessLevel;

    public User(String username, String password, String e_mail, AccessLevel accessLevel) {
        _username = username;
        _password = password;
        _e_mail = e_mail;
        _accessLevel = accessLevel;
    }


    //TODO: I am not sure if this is even needed
    /**
    public String get_username() {
        return _username;
    }

    public String get_password() {
        return _password;
    }

    public String get_e_mail() {
        return _e_mail;
    }

    public AccessLevel get_accessLevel() {
        return _accessLevel;
    }

    public void set_username(String username) {
        _username = username;
    }

    public void set_password(String password) {
        _password = password;
    }

    public void set_e_mail(String e_mail) {
        _e_mail = e_mail;
    }
     */


    //TODO: to be implemented once user status authentication is needed
    public void /**boolean*/ set_accessLevel(/**User user, */AccessLevel accessLevel) {
        /**
        if (user.get_accessLevel().equals(AccessLevel.ADMINISTRATOR)) {
            _accessLevel = accessLevel;
            return true;
        } else {
            return false;
        }
         */
        _accessLevel = accessLevel;
    }

    public void writeNewUserToDatabase() {
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        db.child("users").child(_username).setValue(_username);
        db.child("users").child(_username).child("username").setValue(_username);
        db.child("users").child(_username).child("email").setValue(_e_mail);
        db.child("users").child(_username).child("password").setValue(_password);
        db.child("users").child(_username).child("accessLevel").setValue(_accessLevel.toString());
    }

}
