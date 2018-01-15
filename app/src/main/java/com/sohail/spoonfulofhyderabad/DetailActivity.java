package com.sohail.spoonfulofhyderabad;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.joaquimley.faboptions.FabOptions;
import com.mikhaellopez.circularimageview.CircularImageView;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    TextView nameText;
    CircularImageView imageView;
    private FabOptions mfabOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameText=(TextView)findViewById(R.id.detail_name_text);
        imageView=(CircularImageView)findViewById(R.id.detail_image);
        mfabOptions=(FabOptions)findViewById(R.id.fab_options);
        mfabOptions.setOnClickListener(this);

        String name=getIntent().getStringExtra("name_string");
        String image_link=getIntent().getStringExtra("image_link");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition));
            imageView.setTransitionName("image_transition");
        }
        nameText.setText(name);
        Glide.with(this).load(image_link).into(imageView);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.faboptions_navigate:
                mfabOptions.setButtonColor(R.id.faboptions_navigate, R.color.colorAccent);
                Intent i=new Intent(DetailActivity.this,CouponActivity.class);
                startActivity(i);
                break;

            case R.id.faboptions_coupon:
                mfabOptions.setButtonColor(R.id.faboptions_coupon, R.color.colorAccent);
                Toast.makeText(DetailActivity.this, "Message", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
