package com.sohail.spoonfulofhyderabad;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.transition.TransitionInflater;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.florent37.expectanim.ExpectAnim;
import com.joaquimley.faboptions.FabOptions;
import com.mikhaellopez.circularimageview.CircularImageView;

import static com.github.florent37.expectanim.core.Expectations.alpha;
import static com.github.florent37.expectanim.core.Expectations.height;
import static com.github.florent37.expectanim.core.Expectations.leftOfParent;
import static com.github.florent37.expectanim.core.Expectations.rightOfParent;
import static com.github.florent37.expectanim.core.Expectations.sameCenterVerticalAs;
import static com.github.florent37.expectanim.core.Expectations.scale;
import static com.github.florent37.expectanim.core.Expectations.toRightOf;
import static com.github.florent37.expectanim.core.Expectations.topOfParent;

public class DetailActivity extends AppCompatActivity {

    TextView nameText;
    CircularImageView imageView;
    private FabOptions mfabOptions;
    AppCompatButton button;
    ExpectAnim expectAnim;
    int height ;
    View Background;
    NestedScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameText=(TextView)findViewById(R.id.detail_name_text);
        imageView=(CircularImageView)findViewById(R.id.detail_image);
        String name=getIntent().getStringExtra("name_string");
        String image_link=getIntent().getStringExtra("image_link");


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.shared_element_transition));
            imageView.setTransitionName("image_transition");
        }
        nameText.setText(name);
        Glide.with(this).load(image_link).into(imageView);

        button=(AppCompatButton)findViewById(R.id.follow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DetailActivity.this,CouponActivity.class);
                startActivity(i);
            }
        });
        Background=(View)findViewById(R.id.background);
        scrollView=(NestedScrollView)findViewById(R.id.scrollview);

        this.expectAnim = new ExpectAnim()
                .expect(imageView)
                .toBe(
                        topOfParent().withMarginDp(20),
                        leftOfParent().withMarginDp(20),
                        scale(0.5f, 0.5f)
                )

                .expect(nameText)
                .toBe(
                        toRightOf(imageView).withMarginDp(16),
                        sameCenterVerticalAs(imageView),

                        alpha(0.5f)
                )

                .expect(button)
                .toBe(
                        rightOfParent().withMarginDp(20),
                        sameCenterVerticalAs(imageView)
                )

                .expect(Background)
                .toBe(
                        height(270).withGravity(Gravity.LEFT, Gravity.TOP)
                )

                .toAnimation();


        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                final float percent = (scrollY * 1f) / v.getMaxScrollAmount();
                expectAnim.setPercent(percent);
            }
        });

    }


}
