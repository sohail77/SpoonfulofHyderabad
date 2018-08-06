package com.sohail.spoonfulofhyderabad.Models;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by SOHAIL on 28/01/18.
 */

public class Category_item {

    String category_img;
    String category_txt;



    public String getCategory_img() {
        return category_img;
    }

    public Category_item(String category_img, String category_txt) {
        this.category_img = category_img;
        this.category_txt = category_txt;
    }

    public void setCategory_img(String category_img) {
        this.category_img = category_img;
    }

    public String getCategory_txt() {
        return category_txt;
    }

    public void setCategory_txt(String category_txt) {
        this.category_txt = category_txt;
    }
}
