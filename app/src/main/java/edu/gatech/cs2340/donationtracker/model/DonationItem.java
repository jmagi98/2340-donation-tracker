package edu.gatech.cs2340.donationtracker.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class DonationItem {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
    Timestamp _ts;
    Location _location;
    String _shortDescription;
    String _fullDescription;
    double _value;
    Category _category;

}
