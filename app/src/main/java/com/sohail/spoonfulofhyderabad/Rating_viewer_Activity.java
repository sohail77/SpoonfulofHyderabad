package com.sohail.spoonfulofhyderabad;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
import com.stepstone.apprating.AppRatingDialog;
import com.stepstone.apprating.listener.RatingDialogListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Rating_viewer_Activity extends AppCompatActivity implements RatingDialogListener {


    private static final String TAG =Rating_viewer_Activity.class.getSimpleName() ;
    public List<rating_model> review_list;
    rating_model review;
    private FirebaseFirestore mFirestore;
    private rating_adapter ratingAdapter;
    int AvgRating;
    TextView AvgRating_text,count,Empty_text;
    RecyclerView rating_rv;
    FloatingActionButton review_fab;
    FirebaseAuth auth;
    String name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_viewer_);
        name=getIntent().getStringExtra("name");
        AvgRating=getIntent().getIntExtra("AvgRating",0);
        mFirestore=FirebaseFirestore.getInstance();

        AvgRating_text=(TextView)findViewById(R.id.rating_text_detail);
        count=(TextView)findViewById(R.id.no_of_rating);
        Empty_text=(TextView)findViewById(R.id.reviews_not_found_text);
        rating_rv=(RecyclerView)findViewById(R.id.reviews_rv);
        review_fab=(FloatingActionButton)findViewById(R.id.review_add_fab);

        auth=FirebaseAuth.getInstance();
        AvgRating_text.setText(String.valueOf(AvgRating)+" out of 5 Stars");

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(R.transition.finder_transition));
//        }
        rating_rv.setLayoutManager(new LinearLayoutManager(this));
        review_list=new ArrayList<>();
        ratingAdapter=new rating_adapter(review_list,Rating_viewer_Activity.this);
        rating_rv.setAdapter(ratingAdapter);
        CollectionReference ref=mFirestore.collection("reviews");
        Query query=ref.whereEqualTo("hotel",name);
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                if(e !=null){
                    Log.d(TAG,"Error : " + e.getMessage());
                }
                for (DocumentChange doc:documentSnapshots.getDocumentChanges()){

                    if(doc.getType()==DocumentChange.Type.ADDED) {
                        review=doc.getDocument().toObject(rating_model.class);
                            review_list.add(review);
                            ratingAdapter.notifyDataSetChanged();

                    }
                }

                count.setText("from " +String.valueOf(review_list.size()) +" reviews");
                if (review_list.size()==0){
                    Empty_text.setVisibility(View.VISIBLE);
                    rating_rv.setVisibility(View.GONE);

                }
            }
        });

        review_fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });


    }


    private void showDialog() {
        new AppRatingDialog.Builder()
                .setPositiveButtonText("Submit")
                .setNegativeButtonText("Cancel")
                .setNoteDescriptions(Arrays.asList("Very Bad", "Not good", "Quite ok", "Very Good", "Excellent !!!"))
                .setDefaultRating(2)
                .setTitle("How would you rate this place?")
                .setDescription("Please select some stars and give your feedback")
                .setStarColor(R.color.starColor)
                .setNoteDescriptionTextColor(R.color.noteDescriptionTextColor)
                .setTitleTextColor(R.color.titleTextColor)
                .setDescriptionTextColor(R.color.descriptionTextColor)
                .setHint("Please write your comment here ...")
                .setHintTextColor(R.color.hintTextColor)
                .setCommentTextColor(R.color.commentTextColor)
                .setCommentBackgroundColor(R.color.colorPrimaryDark)
                .setWindowAnimation(R.style.MyDialogFadeAnimation)
                .create(Rating_viewer_Activity.this)
                .show();
    }

    @Override
    public void onNegativeButtonClicked() {

    }

    @Override
    public void onPositiveButtonClicked(int i, String s) {

        review_list.clear();
        final HashMap<String, Object> rating = new HashMap<>();
        rating.put("rating", i);
        rating.put("comment", s);
        rating.put("User", auth.getCurrentUser().getDisplayName());
        rating.put("hotel", name);
        rating.put("email", auth.getCurrentUser().getEmail());
        rating.put("image_url",auth.getCurrentUser().getPhotoUrl().toString());

        mFirestore.collection("reviews").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot documentSnapshots) {
                for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {

                    if (doc.getType() == DocumentChange.Type.ADDED) {
                        review = doc.getDocument().toObject(rating_model.class);
                        if(review.getHotel().equals(name)) {
                            review_list.add(review);
                        }
                    }
                }
                if(review_list.size()>0) {
                    for (int i = 0; i < review_list.size(); i++) {
                        if (review_list.get(i).getEmail().equals(auth.getCurrentUser().getEmail())) {
                            Toast.makeText(Rating_viewer_Activity.this, "You have rated this place already", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                }
                mFirestore.collection("reviews").add(rating).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Rating_viewer_Activity.this, "added", Toast.LENGTH_LONG).show();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Rating_viewer_Activity.this, "failed", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }

    @Override
    public void onNeutralButtonClicked() {

    }
}
