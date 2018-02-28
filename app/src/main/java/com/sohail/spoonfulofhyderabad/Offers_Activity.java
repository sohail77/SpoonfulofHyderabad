package com.sohail.spoonfulofhyderabad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Offers_Activity extends AppCompatActivity {


    private static final String TAG =Offers_Activity.class.getSimpleName() ;
    private FirebaseFirestore mFirestore;
    private List<offers_model> offers_list;
    private offer_item_adapter offerAdapter;
    offers_model offers;
    ImageView back_image;
    RecyclerView rv;
    String name;
    TextView emptyTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers_);
        name=getIntent().getStringExtra("hotel_name");
        emptyTxt=(TextView)findViewById(R.id.emptyTxt);
        back_image=(ImageView)findViewById(R.id.back_image);
        rv=(RecyclerView)findViewById(R.id.offer_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        mFirestore=FirebaseFirestore.getInstance();
        offers_list=new ArrayList<>();
        offerAdapter=new offer_item_adapter(offers_list,Offers_Activity.this);
        rv.setAdapter(offerAdapter);
        CollectionReference ref=mFirestore.collection("coupons");
        Query query=ref.whereEqualTo("hotel",name);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e !=null){
                    Log.d(TAG,"Error : " + e.getMessage());
                }
                for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                    if(doc.getType()==DocumentChange.Type.ADDED) {
                        offers=doc.getDocument().toObject(offers_model.class);
                        offers_list.add(offers);
                        offerAdapter.notifyDataSetChanged();

                    }
                }
                if(offers_list.size()==0){
                    rv.setVisibility(View.GONE);
                    emptyTxt.setVisibility(View.VISIBLE);
                }

            }
        });

        back_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
