package edu.gatech.cs2340.donationtracker.model;

import android.app.Application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GlobalVariables extends Application {
    List<Location> _locations;
    public Map<String, List<DonationItem>> _categories = new HashMap<>();

    public void initMap() {
        Map<String, List<DonationItem>> categories = new HashMap<>();

        categories.put("Clothing", new ArrayList<DonationItem>());
        categories.put("Hat", new ArrayList<DonationItem>());
        categories.put("Kitchen", new ArrayList<DonationItem>());
        categories.put("Electronics", new ArrayList<DonationItem>());
        categories.put("Household", new ArrayList<DonationItem>());
        categories.put("Other", new ArrayList<DonationItem>());
        set_categories(categories);
    }

    public List<Location> getLocations() {
        return _locations;
    }
    public Map<String, List<DonationItem>> getCategories() {
        return _categories;
    }


    public void setLocations(List<Location> lst) {
        _locations = lst;
    }

    public void set_categories(Map<String, List<DonationItem>> m){_categories = m;}

    public void setCategoryKeys(String key) {
        _categories.put(key, new ArrayList<DonationItem>());
    }

    public Set<String> getCategoryKeys() {
        return getCategories().keySet();
    }


}
