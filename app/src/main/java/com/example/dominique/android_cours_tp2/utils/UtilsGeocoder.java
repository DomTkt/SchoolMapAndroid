package com.example.dominique.android_cours_tp2.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class UtilsGeocoder {

    private Context context;

    public UtilsGeocoder() {

    }

    public UtilsGeocoder(Context context) {
        this.context = context;
    }

    public String toUpperCase(String location){
        String locationUpper = location.toUpperCase();
        return locationUpper;
    }

    public String getPostalCodeWithLocation(Context context, double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String postalCode = addresses.get(0).getPostalCode();
        return postalCode;
    }

    public String getStreetNameWithLocation(Context context, double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String streetName = addresses.get(0).getAddressLine(0);
        return streetName;
    }

    public String getCountryNameWithLocation(Context context, double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String countryName = addresses.get(0).getCountryName();
        return countryName;
    }

    public String getCityWithLocation(Context context, double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
        String cityName = addresses.get(0).getLocality();
        return cityName;
    }

}
