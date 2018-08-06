package com.sohail.spoonfulofhyderabad.Models;

/**
 * Created by SOHAIL on 25/02/18.
 */

public class offers_model {

    String desc,title,hotel;

    public offers_model() {
    }

    public offers_model(String desc, String title, String hotel) {
        this.desc = desc;
        this.title = title;
        this.hotel = hotel;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }
}
