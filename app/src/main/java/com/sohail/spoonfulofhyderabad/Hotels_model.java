package com.sohail.spoonfulofhyderabad;

/**
 * Created by SOHAIL on 10/01/18.
 */

public class Hotels_model {
    public String name,image;
    public long contact;

    public Hotels_model() {
    }

    public Hotels_model(String hotel_name, String image, long contact) {
        this.name = hotel_name;
        this.image = image;
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
}
