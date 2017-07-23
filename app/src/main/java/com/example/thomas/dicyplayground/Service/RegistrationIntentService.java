package com.example.thomas.dicyplayground.Service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.thomas.dicyplayground.R;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

/**
 * Created by Thomas on 29.05.2016.
 */
public class RegistrationIntentService extends IntentService
{
    public RegistrationIntentService()
    {
        super("RegistrationIntentService");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public RegistrationIntentService(String name)
    {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        InstanceID instanceID = InstanceID.getInstance(this);
        String token = null;
        try
        {
            token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
            Log.i("RegIntSer", "GCM Registration Token: " + token);

            // TODO: Implement this method to send any registration to your app's servers.
            sendRegistrationToServer(token);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        // [END get_token]

    }

    private void sendRegistrationToServer(String token)
    {
        // TODO: send to server
    }
}
