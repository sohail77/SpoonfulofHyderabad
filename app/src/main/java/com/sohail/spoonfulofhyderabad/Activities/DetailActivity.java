package com.sohail.spoonfulofhyderabad.Activities;

import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.github.florent37.expectanim.ExpectAnim;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.sohail.spoonfulofhyderabad.Adapters.Image_Adapter;
import com.sohail.spoonfulofhyderabad.Hotels_model;
import com.sohail.spoonfulofhyderabad.Models.Images_model;
import com.sohail.spoonfulofhyderabad.Models.rating_model;
import com.sohail.spoonfulofhyderabad.R;

import java.util.ArrayList;
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
    TextView nameText,type_text,rating_text;
    CircularImageView imageView;
    AppCompatButton button;
    ExpectAnim expectAnim;
    int height ;
    View Background;
    NestedScrollView scrollView;
    private FirebaseFirestore mFirestore;
    private List<Hotels_model> hotel_lists;
    Hotels_model hotels;
    FirebaseFirestore reviewRef,imageRef;
    FirebaseAuth auth;
    private List<rating_model> review_lists;
    rating_model reviews;
    int rating,AvgRating;
    String name;
    RecyclerView menuImage_rv;
    Boolean alreadyReviewd=false;
    LinearLayout rating_layout;
    ImageView star_img;
    Images_model menu_image;
    List<Images_model> menu_image_lists;
    Image_Adapter image_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        nameText=(TextView)findViewById(R.id.detail_name_text);
        type_text=(TextView)findViewById(R.id.type);
        imageView=(CircularImageView)findViewById(R.id.detail_image);
        rating_layout=(LinearLayout)findViewById(R.id.rating_layout);
        star_img=(ImageView)findViewById(R.id.star_img);
        rating_text=(TextView)findViewById(R.id.rating_text);
        menuImage_rv=(RecyclerView)findViewById(R.id.menuImage_rv);
        menuImage_rv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        name=getIntent().getStringExtra("name_string");
        final String image_link=getIntent().getStringExtra("image_link");

        review_lists=new ArrayList<>();
        menu_image_lists=new ArrayList<>();
        image_adapter=new Image_Adapter(menu_image_lists,DetailActivity.this);
        reviewRef=FirebaseFirestore.getInstance();
        imageRef=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        menuImage_rv.setAdapter(image_adapter);
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

                getAvgRating();
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


        CollectionReference image_collection_ref=imageRef.collection("menu");
        Query query1=image_collection_ref.whereEqualTo("hotel",name);
        query1.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e !=null){
                    Log.d(TAG,"Error : " + e.getMessage());
                }
                for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                    if(doc.getType()==DocumentChange.Type.ADDED) {
                        menu_image=doc.getDocument().toObject(Images_model.class);
                        menu_image_lists.add(menu_image);
                        image_adapter.notifyDataSetChanged();

                    }
                }
            }
        });



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
                Intent i=new Intent(DetailActivity.this,Offers_Activity.class);
                i.putExtra("hotel_name",name);
                startActivity(i);
//                showDialog();
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


        rating_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(DetailActivity.this,Rating_viewer_Activity.class);
                i.putExtra("name",name);
                i.putExtra("AvgRating",AvgRating);
                ActivityOptionsCompat optionsCompat=ActivityOptionsCompat.makeSceneTransitionAnimation(DetailActivity.this,star_img, ViewCompat.getTransitionName(star_img));
                startActivity(i,optionsCompat.toBundle());
            }
        });



    }



   public void getAvgRating(){
       reviewRef.collection("reviews").addSnapshotListener(new EventListener<QuerySnapshot>() {
           @Override
           public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
               if(e !=null){
                   Log.d(TAG,"Error : " + e.getMessage());
               }
               for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                   if(doc.getType()==DocumentChange.Type.ADDED) {
                       reviews=doc.getDocument().toObject(rating_model.class);
                       if(reviews.getHotel().equals(name)) {
                           review_lists.add(reviews);
                           rating += reviews.getRating();
                       }

                   }
               }
               if(review_lists.size()>0) {
                   AvgRating = rating / review_lists.size();
                   rating_text.setText(String.valueOf(AvgRating) +" out of 5 Stars");
               }else {
                   rating_text.setText("No reviews");
               }
           }
       });

    }
}
