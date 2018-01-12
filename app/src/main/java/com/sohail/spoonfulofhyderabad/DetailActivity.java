package com.sohail.spoonfulofhyderabad;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

public class DetailActivity extends AppCompatActivity {

    TextView nameText;
    CircularImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameText=(TextView)findViewById(R.id.detail_name_text);
        imageView=(CircularImageView)findViewById(R.id.detail_image);

        String name=getIntent().getStringExtra("name_string");
        String image_link=getIntent().getStringExtra("image_link");

        nameText.setText(name);
        Glide.with(this).load(image_link).into(imageView);

    }
}
