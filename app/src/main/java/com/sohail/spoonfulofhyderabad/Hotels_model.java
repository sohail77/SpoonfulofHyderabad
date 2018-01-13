package com.sohail.spoonfulofhyderabad;

/**
 * Created by SOHAIL on 10/01/18.
 */

public class Hotels_model {
    public String name,image,address,type;
    public long contact;


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
