package edu.gatech.cs2340.donationtracker.model;

public class Location {
    private String _name;
    private double _latitude;
    private double _longitude;
    private String _streetAddress;
    private String _city;
    private String _state;
    private int _zip;
    private LocationType _type;
    private String _phone_number;
    private String _website;

    public Location(String name, double latitude, double longitude, String streetAddress, String city, String state, int zip, LocationType locationType, String phoneNumber, String website) {
        _name = name;
        _latitude = latitude;
        _longitude = longitude;
        _streetAddress = streetAddress;
        _city = city;
        _state = state;
        _zip = zip;
        _type = locationType;
        _phone_number = phoneNumber;
        _website = website;
    }
}
