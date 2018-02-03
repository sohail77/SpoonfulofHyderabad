package com.sohail.spoonfulofhyderabad;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.firestore.GeoPoint;

import java.util.HashMap;

/**
 * Created by SOHAIL on 10/01/18.
 */

public class Hotels_model {
    public String name,image,address,type;
    int budget;


    public long contact;
    GeoPoint location;
    double lat,longitude;

    public Hotels_model() {
    }
    public Hotels_model(String name, String image, String address, String type, long contact) {
        this.name = name;
        this.image = image;
        this.address = address;
        this.type = type;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }


    public String getAddress() {

        return address;
    }
    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public GeoPoint getLocation() {
        return location;
    }

    public void setLocation(GeoPoint location) {
        this.location = location;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
