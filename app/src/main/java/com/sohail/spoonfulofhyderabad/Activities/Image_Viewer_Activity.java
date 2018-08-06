package com.sohail.spoonfulofhyderabad.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.sohail.spoonfulofhyderabad.R;

public class Image_Viewer_Activity extends AppCompatActivity {

    PhotoView photoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image__viewer_);
        photoView=(PhotoView)findViewById(R.id.photo_view);
        String imageUrl=getIntent().getStringExtra("imageUrl");
        Glide.with(Image_Viewer_Activity.this).load(imageUrl).into(photoView);

    }
}
