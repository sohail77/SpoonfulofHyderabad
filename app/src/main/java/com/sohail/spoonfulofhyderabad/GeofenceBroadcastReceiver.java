package com.sohail.spoonfulofhyderabad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by SOHAIL on 24/01/18.
 */

public class GeofenceBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        // Enqueues a JobIntentService passing the context and intent as parameters
        GeofenceTransitionsJobIntentService.enqueueWork(context, intent);
    }
}
