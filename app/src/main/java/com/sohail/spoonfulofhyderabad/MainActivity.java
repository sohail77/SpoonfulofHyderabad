package com.sohail.spoonfulofhyderabad;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.Orientation;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements DiscreteScrollView.OnItemChangedListener,
        View.OnClickListener{

    private static final String TAG ="Firelog" ;
    private static final int RC_SIGN_IN = 1;
    protected ArrayList<Geofence> mGeofenceList;
    protected GoogleApiClient mGoogleApiClient;
    private Button mAddGeofencesButton;
    private FirebaseFirestore mFirestore;
    private List<Hotels_model> hotel_lists;
    private Hotel_adatpters hotel_adatpters;
    private DiscreteScrollView itemPicker;
    Button logoutBtn;
    FirebaseAuth auth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser() !=null)
        {
//            Toast.makeText(this,"Welcome "+auth.getCurrentUser().getEmail(),Toast.LENGTH_LONG).show();

        }else {
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(
                                    Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.GOOGLE_PROVIDER).build(),
                                            new AuthUI.IdpConfig.Builder(AuthUI.FACEBOOK_PROVIDER).build()
                                    ))
                            .setTheme(R.style.IntroScreen)
                            .build(),
                    RC_SIGN_IN);
        }



        hotel_lists=new ArrayList<>();
        hotel_adatpters=new Hotel_adatpters(MainActivity.this,hotel_lists);
        itemPicker = (DiscreteScrollView) findViewById(R.id.item_picker);
        itemPicker.setOrientation(Orientation.HORIZONTAL);
        itemPicker.addOnItemChangedListener(this);
        itemPicker.setAdapter(hotel_adatpters);
        itemPicker.setItemTransitionTimeMillis(150);
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
                        Hotels_model hotels=doc.getDocument().toObject(Hotels_model.class);
                        hotel_lists.add(hotels);
                       hotel_adatpters.notifyDataSetChanged();
                    }
                }
            }
        });

        logoutBtn=(Button)findViewById(R.id.logout_btn);
        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AuthUI.getInstance().signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                finish();
                            }
                        });
            }
        });



//        mAddGeofencesButton = (Button) findViewById(R.id.add_geofences_button);
//        // Empty list for storing geofences.
//        mGeofenceList = new ArrayList<Geofence>();
//
//        // Get the geofences used. Geofence data is hard coded in this sample.
//        populateGeofenceList();
//
//        // Kick off the request to build GoogleApiClient.
//        buildGoogleApiClient();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==RC_SIGN_IN){
            if(resultCode==RESULT_OK){
                Toast.makeText(this,"Welcome "+auth.getCurrentUser().getDisplayName(),Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onCurrentItemChanged(@Nullable RecyclerView.ViewHolder viewHolder, int adapterPosition) {
      //  int positionInDataSet = infiniteAdapter.getRealPosition(adapterPosition);
//      onItemChanged(hotel_lists.get(positionInDataSet));

    }


//    protected synchronized void buildGoogleApiClient() {
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .addApi(LocationServices.API)
//                .build();
//    }
//
//    public void populateGeofenceList() {
//        for (Map.Entry<String, LatLng> entry : Constants.LANDMARKS.entrySet()) {
//            mGeofenceList.add(new Geofence.Builder()
//                    .setRequestId(entry.getKey())
//                    .setCircularRegion(
//                            entry.getValue().latitude,
//                            entry.getValue().longitude,
//                            Constants.GEOFENCE_RADIUS_IN_METERS
//                    )
//                    .setExpirationDuration(Constants.GEOFENCE_EXPIRATION_IN_MILLISECONDS)
//                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER |
//                            Geofence.GEOFENCE_TRANSITION_EXIT)
//                    .build());
//        }
//    }
//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        if (!mGoogleApiClient.isConnecting() || !mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.connect();
//        }
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        if (mGoogleApiClient.isConnecting() || mGoogleApiClient.isConnected()) {
//            mGoogleApiClient.disconnect();
//        }
//    }
//
//    @Override
//    public void onConnected(Bundle connectionHint) {
//        Log.d("Connected","Google");
//    }
//
//    @Override
//    public void onConnectionFailed(ConnectionResult result) {
//        // Do something with result.getErrorCode());
//        Log.d("Not Connected","Google");
//    }
//
//    @Override
//    public void onConnectionSuspended(int cause) {
//        mGoogleApiClient.connect();
//    }
//
//    public void addGeofencesButtonHandler(View view) {
//        if (!mGoogleApiClient.isConnected()) {
//            Toast.makeText(this, "Google API Client not connected!", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        try {
//            LocationServices.GeofencingApi.addGeofences(
//                    mGoogleApiClient,
//                    getGeofencingRequest(),
//                    getGeofencePendingIntent()
//            ).setResultCallback(this); // Result processed in onResult().
//        } catch (SecurityException securityException) {
//            // Catch exception generated if the app does not use ACCESS_FINE_LOCATION permission.
//        }
//    }
//
//    private GeofencingRequest getGeofencingRequest() {
//        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
//        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
//        builder.addGeofences(mGeofenceList);
//        return builder.build();
//    }
//
//    private PendingIntent getGeofencePendingIntent() {
//        Intent intent = new Intent(this, GeofenceTransitionsIntentService.class);
//        // We use FLAG_UPDATE_CURRENT so that we get the
//        //same pending intent back when calling addgeoFences()
//        return PendingIntent.getService(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//    }
//    public void onResult(Status status) {
//        if (status.isSuccess()) {
//            Toast.makeText(
//                    this,
//                    "Geofences Added",
//                    Toast.LENGTH_SHORT
//            ).show();
//        } else {
//            // Get the status code for the error and log it using a user-friendly message.
//            System.out.println("Error");
//        }
//    }
}
