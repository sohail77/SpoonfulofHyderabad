package com.sohail.spoonfulofhyderabad;

import android.app.PendingIntent;
import android.content.Context;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Created by SOHAIL on 06/01/18.
 */

public class Geofencing {

//    private List<Geofence> mGeofenceList;
//    private PendingIntent mGeofencePendingIntent;
//    private GoogleApiClient mGoogleApiClient;
//    private Context mContext;
//    private static final float GEOFENCE_RADIUS = 500; // 50 meters
//    private static final long GEOFENCE_TIMEOUT = 24 * 60 * 60 * 1000; // 24 hours
//    public List<places<Double, Double>> placesList=new ArrayList<places<Double, Double>>();
//
//
//    public Geofencing(Context context, GoogleApiClient client) {
//        mContext = context;
//        mGoogleApiClient = client;
//        mGeofencePendingIntent = null;
//        }
//
//    public void updateGeofencesList() {
//        String uniqueID = UUID.randomUUID().toString();
//
//        placesList.add(new places<Double, Double>(17.4101080, 78.4402424));
//        placesList.add(new places<Double, Double>(17.4282648, 78.442848));
//        placesList.add(new places<Double, Double>(17.3845578, 78.4649667));
//        placesList.add(new places<Double, Double>(17.4180039, 78.5273363));
//        placesList.add(new places<Double, Double>(17.494568, 78.3920556));
//
//        for (places<Double, Double> place : placesList) {
//            double placeLat = place.getLat();
//            double placeLon = place.getLon();
//            Geofence geofence = new Geofence.Builder()
//                    .setRequestId(uniqueID)
//                    .setExpirationDuration(GEOFENCE_TIMEOUT)
//                    .setCircularRegion(placeLat, placeLon, GEOFENCE_RADIUS)
//                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
//                    .build();
//            // Add it to the list
//            mGeofenceList.add(geofence);
//        }
//    }
//        /***
//         * Creates a GeofencingRequest object using the mGeofenceList ArrayList of Geofences
//         * Used by {@code #registerGeofences}
//         *
//         * @return the GeofencingRequest object
//         */
//
//    private GeofencingRequest getGeofencingRequest() {
//        GeofencingRequest.Builder builder = new GeofencingRequest.Builder();
//        builder.setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER);
//        builder.addGeofences(mGeofenceList);
//        return builder.build();
//    }
}

