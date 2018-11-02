package edu.gatech.cs2340.donationtracker.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DonationItem {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");

    //TODO: make getters and setters
    public Timestamp _ts;
    public String _location;
    public String _shortDescription;
    public String _fullDescription;
    public String _value;
    public Category _category;

    public DonationItem( Timestamp timestamp, String location, String shortDescription, String fullDescription, String value, Category category) {
        _ts = timestamp;
        _location = location;
        _shortDescription = shortDescription;
        _fullDescription = fullDescription;
        _value = value;
        _category = category;
    }

    public String toString() {
        return _shortDescription;
    }


}
