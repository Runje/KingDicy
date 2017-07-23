package com.example.thomas.dicyplayground.Service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GcmListenerService;

/**
 * Created by Thomas on 28.05.2016.
 */
public class DicyReceiver extends GcmListenerService
{
    public final String TAG = "DicyReceiver";
    @Override
    public void onMessageReceived(String from, Bundle data) {
        String message = data.getString("message");
        String para = data.getString("other-parameter");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "Parameter: " + para);

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(getApplicationContext(), "Message: " + message, Toast.LENGTH_LONG).show();
            }
        });

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }

        // ...
    }
}
