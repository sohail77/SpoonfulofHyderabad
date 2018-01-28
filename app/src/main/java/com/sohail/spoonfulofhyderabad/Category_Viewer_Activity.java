package com.sohail.spoonfulofhyderabad;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class Category_Viewer_Activity extends AppCompatActivity {


    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category__viewer_);
        imageView=(ImageView)findViewById(R.id.Category_viewer_img);
        textView=(TextView)findViewById(R.id.Category_viewer_txt);

        String name=getIntent().getStringExtra("Category_name");
        String image=getIntent().getStringExtra("Category_img");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition));
            imageView.setTransitionName("image_transi");
        }

        textView.setText(name);
        Glide.with(this).load(image).into(imageView);
    }
}
