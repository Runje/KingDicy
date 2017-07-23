package com.example.thomas.dicyplayground.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.thomas.dicyplayground.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by Thomas on 28.05.2016.
 */
public class DicyService extends IntentService
{
    public DicyService()
    {
        super("DicyService");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DicyService(String name)
    {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Log.d("DicyService", "Handling intent");
        InstanceID instanceID = InstanceID.getInstance(this);
        try
        {
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    // server api key
    // AIzaSyCSWoP_a7yH3EXLKnLjY2wk7MG2-nUWqtQ

    // sender id
    // 192923509881
}
