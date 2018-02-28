package com.sohail.spoonfulofhyderabad;

import android.gesture.GestureLibraries;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class Profile_Activity extends AppCompatActivity {

    private static final String TAG = Profile_Activity.class.getSimpleName();
    FirebaseFirestore mFirestore;
    TextView username,usermail;
    CircularImageView profileImage;
    Orders_Model orders_model;
    List<Orders_Model> profile_order_list;
    Profile_item_Adapter profile_item_adapter;
    RecyclerView rv;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_);

        mFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        username=(TextView)findViewById(R.id.profileName);
        usermail=(TextView)findViewById(R.id.profileEmail);
        profileImage=(CircularImageView)findViewById(R.id.profileImage);
        rv=(RecyclerView)findViewById(R.id.profile_rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RequestOptions options=new RequestOptions()
                .error(R.drawable.icon_profile);
        Glide.with(Profile_Activity.this).load(auth.getCurrentUser().getPhotoUrl()).apply(options).into(profileImage);
        username.setText(auth.getCurrentUser().getDisplayName());
        usermail.setText(auth.getCurrentUser().getEmail());

        profile_order_list=new ArrayList<>();
        profile_item_adapter=new Profile_item_Adapter(profile_order_list,Profile_Activity.this);
        rv.setAdapter(profile_item_adapter);
        CollectionReference ref=mFirestore.collection("orders");
        Query query=ref.whereEqualTo("user",auth.getCurrentUser().getEmail());
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e !=null){
                    Log.d(TAG,"Error : " + e.getMessage());
                }
                for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                    if(doc.getType()==DocumentChange.Type.ADDED) {
                        orders_model=doc.getDocument().toObject(Orders_Model.class);
                        profile_order_list.add(orders_model);
                        profile_item_adapter.notifyDataSetChanged();

                    }
                }
            }
        });
    }
}
