package com.sohail.spoonfulofhyderabad;

import android.animation.Animator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.dynamitechetan.fogviewlibrary.FogView;


public class CouponActivity extends AppCompatActivity {


    private float mDownX;
    private float mDownY;
    private final float SCROLL_THRESHOLD = 10;
    FogView fogView;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coupon);
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
