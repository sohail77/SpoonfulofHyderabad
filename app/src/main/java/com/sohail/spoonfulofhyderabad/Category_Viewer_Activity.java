package com.sohail.spoonfulofhyderabad;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.List;

public class Category_Viewer_Activity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener<RecyclerView.ViewHolder>, View.OnClickListener {


    private static final String TAG =Category_Viewer_Activity.class.getSimpleName() ;
    ImageView imageView;
    TextView textView;
    private FirebaseFirestore mFirestore;
    private List<Hotels_model> hotel_lists;
    private Category_list_item_adapter hotel_adatpters;
    private DiscreteScrollView itemPicker;
    Hotels_model hotels;



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
            textView.setTransitionName("image_transi");
        }

        textView.setText(name);
        Glide.with(this).load(image).into(imageView);



        hotel_lists=new ArrayList<>();
        hotel_adatpters=new Category_list_item_adapter(Category_Viewer_Activity.this,hotel_lists);
        itemPicker = (DiscreteScrollView) findViewById(R.id.item_picker_cat);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        itemPicker.setAdapter(hotel_adatpters);
        itemPicker.setItemTransitionTimeMillis(150);
        itemPicker.addItemDecoration(new CategoryRecylerItemDecorator());

//        itemPicker.setSlideOnFlingThreshold(1500);
        itemPicker.setItemTransformer(new ScaleTransformer.Builder()
                .setMinScale(0.6f)
                .build());

        itemPicker.setOnClickListener(this);


        mFirestore =FirebaseFirestore.getInstance();
        mFirestore.collection("hotels").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e !=null){
                    Log.d(TAG,"Error : " + e.getMessage());
                }
                for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                    if(doc.getType()==DocumentChange.Type.ADDED) {
                        hotels=doc.getDocument().toObject(Hotels_model.class);
                        hotel_lists.add(hotels);
                        hotel_adatpters.notifyDataSetChanged();
                    }
                }

            }

        });


    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {

    }

    @Override
    public void onClick(View view) {

    }
}
