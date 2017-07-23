package com.example.thomas.dicyplayground;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.Communication.DicyClient;
import com.example.Communication.DicyMessage;
import com.example.Communication.DicyMessageReceivedListener;
import com.example.Communication.Messages.CommandMessage;
import com.example.Communication.Messages.StartGameMessage;
import com.example.OldModel.Logger;
import com.example.OnConnectionChangedListener;
import com.example.thomas.dicyplayground.util.ActivityUtils;

import javax.inject.Inject;

public class StartGameActivity extends AppCompatActivity implements DicyMessageReceivedListener, OnConnectionChangedListener
{

    @Inject
    DicyClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        DicyApp.component().inject(this);

        client.addOnConnectionChangedListener(this);
        client.setDicyMessageReceivedListener(this);
        //client.connect();

    }

    @Override
    public void onReceivedDicyMessage(DicyMessage msg)
    {
        switch(msg.getName())
        {
            case StartGameMessage.Name:
                StartGameMessage startGameMessage = (StartGameMessage) msg;
                Logger.i("Starting game...");
                Intent intent = new Intent(this, GameActivity.class);

                ActivityUtils.putToIntent(intent, startGameMessage.getGame());
                ActivityUtils.putIsOnlineToIntent(intent, true);
                startActivity(intent);
                break;
            default:
                Logger.e("Received unknown message in StartGameActivity: " + msg.getName());
        }
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        client.stop();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        client.connect();
    }

    @Override
    public void onConnectionChanged(boolean b)
    {
        if (b)
        {
            Logger.i("Connected");

            client.sendDicyMessage(CommandMessage.reqGameId());
        }
        else
        {
            Logger.i("Disconnected");
        }
    }
}
