package com.sohail.spoonfulofhyderabad.Models;

/**
 * Created by SOHAIL on 02/03/18.
 */

public class Images_model {

    String image,hotel;

    public Images_model() {
    }

    public Images_model(String image, String hotel) {
        this.image = image;
        this.hotel = hotel;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
}
