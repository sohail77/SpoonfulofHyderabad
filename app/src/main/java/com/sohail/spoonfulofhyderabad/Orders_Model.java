package com.sohail.spoonfulofhyderabad;

/**
 * Created by SOHAIL on 25/02/18.
 */

public class Orders_Model {

    String user,hotel,code,title;
    boolean used;



    public Orders_Model() {
    }

    public Orders_Model(String user, String hotel, String code, String title) {
        this.user = user;
        this.hotel = hotel;
        this.code = code;
        this.title = title;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }
}
