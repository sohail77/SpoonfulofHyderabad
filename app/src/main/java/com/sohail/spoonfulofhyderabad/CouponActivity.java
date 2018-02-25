package com.sohail.spoonfulofhyderabad;

import android.animation.Animator;
import android.os.Trace;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.dynamitechetan.fogviewlibrary.FogView;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.ncorti.slidetoact.SlideToActView;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


public class CouponActivity extends AppCompatActivity {


    private static final String TAG =CouponActivity.class.getSimpleName() ;
    private float mDownX;
    private float mDownY;
    private final float SCROLL_THRESHOLD = 10;
    FogView fogView;
    FrameLayout frameLayout;
    String coupon_title,hotel_name;
    FirebaseFirestore mFirestore;
    FirebaseAuth auth;

    Orders_Model orders;
    SlideToActView coupon_slide;
    LottieAnimationView animationView;
    TextView coupon_txt,congratulations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
        coupon_slide=(SlideToActView)findViewById(R.id.slide_coupon);
        animationView=(LottieAnimationView)findViewById(R.id.animation_view_coupon);
        coupon_txt=(TextView)findViewById(R.id.coupon_txt);
        congratulations=(TextView)findViewById(R.id.congo_txt);
        coupon_title=getIntent().getStringExtra("coupon_title");
        hotel_name=getIntent().getStringExtra("hotel_name");

        mFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();

        CollectionReference ref=mFirestore.collection("orders");
        Query query=ref.whereEqualTo("hotel",hotel_name).whereEqualTo("title",coupon_title).whereEqualTo("user",auth.getCurrentUser().getEmail()).whereEqualTo("used",false);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e !=null){
                    Log.d(TAG,"Error : " + e.getMessage());
                }
                for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                    if(doc.getType()==DocumentChange.Type.ADDED) {
                        orders=doc.getDocument().toObject(Orders_Model.class);
                        coupon_txt.setText(orders.getCode());
                        coupon_slide.setVisibility(View.GONE);

                    }
                }


            }
        });


        coupon_slide.setOnSlideCompleteListener(new SlideToActView.OnSlideCompleteListener() {
            @Override
            public void onSlideComplete(SlideToActView slideToActView) {
                animationView.setVisibility(View.VISIBLE);
                animationView.playAnimation();
                String uuid = UUID.randomUUID().toString();
                String code=uuid.substring(0,7);
                coupon_txt.setText(code);
                coupon_txt.setVisibility(View.VISIBLE);
                congratulations.setVisibility(View.VISIBLE);
                final HashMap<String, Object> coupon = new HashMap<>();
                coupon.put("code", code);
                coupon.put("hotel", hotel_name);
                coupon.put("title", coupon_title);
                coupon.put("used", false);
                coupon.put("user", auth.getCurrentUser().getEmail());
                mFirestore.collection("orders").add(coupon).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(CouponActivity.this, "added", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(CouponActivity.this, "failed", Toast.LENGTH_LONG).show();

                    }
                });
            }
        });



//        frameLayout = (FrameLayout) findViewById(R.id.coupon_frame);
//        fogView = (FogView) findViewById(R.id.fog);
//        fogView.setOnTouchListener(new MyTouchListener());

    }
//
//    private final class MyTouchListener implements View.OnTouchListener {
//        public boolean onTouch(View view, MotionEvent motionEvent) {
//            if (motionEvent.getAction() == MotionEvent.ACTION_SCROLL) {
//
//                mDownX=motionEvent.getX();
//                mDownY=motionEvent.getY();
//                if (Math.abs(mDownX - motionEvent.getX()) > SCROLL_THRESHOLD || Math.abs(mDownY - motionEvent.getY()) > SCROLL_THRESHOLD) {
//                    Toast.makeText(CouponActivity.this, "Dragged", Toast.LENGTH_SHORT).show();
//                    return true;
//                }
//
//            } else {
//                return false;
//            }
//            return false;
//        }
//    }


    }
