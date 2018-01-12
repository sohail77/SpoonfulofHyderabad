package com.sohail.spoonfulofhyderabad;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

/**
 * Created by SOHAIL on 06/01/18.
 */

public class Constants {

    public static final long GEOFENCE_EXPIRATION_IN_MILLISECONDS = 60 * 60 * 1000;
    public static final float GEOFENCE_RADIUS_IN_METERS = 500;

    public static final HashMap<String, LatLng> LANDMARKS = new HashMap<String, LatLng>();
    static {
        // San Francisco International Airport.
        LANDMARKS.put("Mamagato", new LatLng(17.4101080, 78.4402424));

        // Googleplex.
        LANDMARKS.put("Muffakham jah", new LatLng(17.4282648, 78.442848));

        // Test
        LANDMARKS.put("Deccan", new LatLng(17.3845578, 78.4649667));

        LANDMARKS.put("Osmania", new LatLng(17.4180039, 78.5273363));
        LANDMARKS.put("JNTU", new LatLng(17.494568, 78.3920556));
    }
}
