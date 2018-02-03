package com.sohail.spoonfulofhyderabad;

import android.content.Intent;
import android.os.Build;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.github.florent37.expectanim.ExpectAnim;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.joaquimley.faboptions.FabOptions;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import static com.github.florent37.expectanim.core.Expectations.alpha;
import static com.github.florent37.expectanim.core.Expectations.height;
import static com.github.florent37.expectanim.core.Expectations.leftOfParent;
import static com.github.florent37.expectanim.core.Expectations.rightOfParent;
import static com.github.florent37.expectanim.core.Expectations.sameCenterVerticalAs;
import static com.github.florent37.expectanim.core.Expectations.scale;
import static com.github.florent37.expectanim.core.Expectations.toRightOf;
import static com.github.florent37.expectanim.core.Expectations.topOfParent;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();
    TextView nameText,type_text;
    CircularImageView imageView;
    AppCompatButton button;
    ExpectAnim expectAnim;
    int height ;
    View Background;
    NestedScrollView scrollView;
    private FirebaseFirestore mFirestore;
    private List<Hotels_model> hotel_lists;
    Hotels_model hotels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameText=(TextView)findViewById(R.id.detail_name_text);
        type_text=(TextView)findViewById(R.id.type);
        imageView=(CircularImageView)findViewById(R.id.detail_image);
        String name=getIntent().getStringExtra("name_string");
        final String image_link=getIntent().getStringExtra("image_link");



        mFirestore=FirebaseFirestore.getInstance();
        CollectionReference ref=mFirestore.collection("hotels");
        Query query=ref.whereEqualTo("name",name);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e !=null){
                    Log.d(TAG,"Error : " + e.getMessage());
                }
                for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                    if(doc.getType()==DocumentChange.Type.ADDED) {
                        hotels=doc.getDocument().toObject(Hotels_model.class);
                        type_text.setText(hotels.getType());
//                        hotel_lists.add(hotels);

                    }
                }
            }
        });

        Intent intent=getIntent();

        if(intent.hasExtra("Notification_intent")){
            name=getIntent().getStringExtra("Notification_intent");
            query=ref.whereEqualTo("name",name);
            query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                    if(e !=null){
                        Log.d(TAG,"Error : " + e.getMessage());
                    }
                    for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                        if(doc.getType()==DocumentChange.Type.ADDED) {
                            hotels=doc.getDocument().toObject(Hotels_model.class);
                            type_text.setText(hotels.getType());
//                        hotel_lists.add(hotels);
                            String image_load=hotels.getImage();
                            Glide.with(DetailActivity.this).load(image_load).into(imageView);
                        }
                    }
                }
            });
        }



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
