package com.sohail.spoonfulofhyderabad.Activities;

import android.os.Build;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.sohail.spoonfulofhyderabad.Adapters.Budget_Item_Adapter;
import com.sohail.spoonfulofhyderabad.Hotels_model;
import com.sohail.spoonfulofhyderabad.R;

import java.util.ArrayList;
import java.util.List;

public class BudgetFinderActivity extends AppCompatActivity {


    private static final String TAG =BudgetFinderActivity.class.getSimpleName() ;
    private FirebaseFirestore mFirestore;
    private List<Hotels_model> hotel_lists;
    private Budget_Item_Adapter hotel_adatpters;
    Hotels_model hotels;
    RecyclerView rv;
    CollectionReference ref;
    ImageView finder_back_btn;
    int budget;
    EditText people_txt,amount_txt;
    TextView not_found;
    int people,amount;
    FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_finder);
        rv=(RecyclerView)findViewById(R.id.filter_rv);
        people_txt=(EditText)findViewById(R.id.people_txt);
        amount_txt=(EditText)findViewById(R.id.amount_txt);
        not_found=(TextView)findViewById(R.id.not_found_text);
        fab=(FloatingActionButton)findViewById(R.id.fab);
        finder_back_btn=(ImageView)findViewById(R.id.finder_back_btn_img);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.finder_transition));
        }

        rv.setLayoutManager(new LinearLayoutManager(this));
        hotel_lists=new ArrayList<>();
        hotel_adatpters=new Budget_Item_Adapter(hotel_lists,BudgetFinderActivity.this);
        mFirestore=FirebaseFirestore.getInstance();
         ref=mFirestore.collection("hotels");
        rv.setAdapter(hotel_adatpters);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hotel_lists.clear();
                hotel_adatpters.notifyDataSetChanged();
                if ((!people_txt.getText().toString().equals("")) && (!amount_txt.getText().toString().equals(""))) {

                    people = Integer.parseInt(people_txt.getText().toString());
                    amount = Integer.parseInt(amount_txt.getText().toString());
                    budget = amount / people;
                    Query query = ref.whereLessThanOrEqualTo("budget", budget);
                    query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                        @Override
                        public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                            if (e != null) {
                                Log.d(TAG, "Error : " + e.getMessage());
                            }
                            for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                                if (doc.getType() == DocumentChange.Type.ADDED) {

                                    hotels = doc.getDocument().toObject(Hotels_model.class);
                                    hotel_lists.add(hotels);
                                    hotel_adatpters.notifyDataSetChanged();


                                }
                            }
                            if (hotel_adatpters.getItemCount() == 0) {
                                not_found.setVisibility(View.VISIBLE);
                            } else {
                                not_found.setVisibility(View.GONE);
                                rv.setVisibility(View.VISIBLE);
                            }
                        }
                    });
                }else {
                    Toast.makeText(BudgetFinderActivity.this,"Enter the details",Toast.LENGTH_LONG).show();
                }
            }

            });





        finder_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}