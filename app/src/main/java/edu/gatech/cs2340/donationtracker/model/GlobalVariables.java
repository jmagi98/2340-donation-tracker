package edu.gatech.cs2340.donationtracker.model;

import android.app.Application;

import java.util.List;

public class GlobalVariables extends Application {
    List<Location> _locations;

    public List<Location> getLocations() {
        return _locations;
    }
}
