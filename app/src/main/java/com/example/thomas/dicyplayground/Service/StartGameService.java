package com.example.thomas.dicyplayground.Service;

import android.app.IntentService;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.thomas.dicyplayground.GameActivity;
import com.example.thomas.dicyplayground.OnlineLobbyActivity;

/**
 * Created by Thomas on 30.05.2016.
 */
public class StartGameService extends IntentService
{
    public StartGameService()
    {
        super("StartGame");
    }
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public StartGameService(String name)
    {
        super(name);
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        Handler handler = new Handler(Looper.getMainLooper());
        Log.e("STARTGAMESERVICE", "IS RUNNING!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        handler.post(new Runnable()
        {
            @Override
            public void run()
            {
                Toast.makeText(getApplicationContext(), "StartGameService", Toast.LENGTH_LONG).show();
            }
        });
        Intent dialogIntent = new Intent(this, OnlineLobbyActivity.class);
        dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(dialogIntent);
    }
}
