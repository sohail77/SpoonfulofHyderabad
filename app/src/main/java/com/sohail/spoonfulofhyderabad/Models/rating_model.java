package com.sohail.spoonfulofhyderabad.Models;

/**
 * Created by SOHAIL on 05/02/18.
 */

public class rating_model {

    int rating;
    String comment,User,hotel,email,image_url;

    public rating_model() {
    }


    public String getEmail() {
        return email;
    }



    public rating_model(int rating, String comment, String user, String hotel, String email, String image_url) {
        this.rating = rating;
        this.comment = comment;
        User = user;
        this.hotel = hotel;
        this.email = email;
        this.image_url = image_url;
    }


    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
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
        return User;
    }

    public void setUser(String User) {
        this.User = User;
    }
}
