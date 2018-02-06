package com.sohail.spoonfulofhyderabad;

/**
 * Created by SOHAIL on 05/02/18.
 */

public class rating_model {

    int rating;
    String comment,user,hotel,email;

    public rating_model() {
    }

    public rating_model(int rating, String comment, String user) {
        this.rating = rating;
        this.comment = comment;
        this.user = user;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
