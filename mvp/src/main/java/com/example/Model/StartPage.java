package com.example.Model;

import com.example.Communication.DicyClient;
import com.example.Communication.DicyMessage;
import com.example.Communication.DicyMessageReceivedListener;
import com.example.Communication.Messages.GameListReqMessage;
import com.example.Communication.Messages.GameListResMessage;
import com.example.Communication.Messages.ResumeGameMessage;
import com.example.Communication.Messages.StartGameMessage;
import com.example.OldModel.Logger;
import com.example.OnConnectionChangedListener;

import java.util.List;

/**
 * Created by Thomas on 16.04.2016.
 */
public class StartPage implements DicyMessageReceivedListener, OnConnectionChangedListener
{
    StartPageInteractor interactor;
    DicyClient client;
    String playerId;
    private List<DicyGame> games;

    public StartPage(StartPageInteractor interactor, DicyClient client, String playerId)
    {
        this.interactor = interactor;
        this.client = client;
        this.playerId = playerId;
    }

    private void requestGames()
    {
        Logger.i("Requesting Games");
        client.sendDicyMessage(new GameListReqMessage());
    }

    @Override
    public void onReceivedDicyMessage(DicyMessage msg)
    {
        Logger.i("Dicymessage received(startpage): " + msg.getName());
        switch(msg.getName())
        {
            case GameListResMessage.Name:
                GameListResMessage gameListResMessage = (GameListResMessage) msg;
                games = gameListResMessage.getGames();
                Logger.i("games: " + games.size());
                interactor.setResumeGameEnabled(games.size() > 0);
                break;
            case StartGameMessage.Name:
                StartGameMessage startGameMessage = (StartGameMessage) msg;
                // TODO: players
                interactor.startGame(startGameMessage.getGame());
                break;
            default:
                Logger.e("Unknown Message in StartPage received");
                break;
        }
    }

    public void start()
    {
        Logger.i("Starting Start Page");
        client.addOnConnectionChangedListener(this);
        client.setDicyMessageReceivedListener(this);
        client.start();
        interactor.setResumeGameEnabled(false);
        interactor.setOnlineLobbyEnabled(client.isConnected());
        if (client.isConnected())
        {
            requestGames();
        }
    }

    public void stop()
    {
        client.stop();
        client.removeOnConnectionChangedListener(this);
    }

    @Override
    public void onConnectionChanged(boolean connected)
    {
        Logger.i("Connection changed in StartPage to " + connected);
        if (connected)
        {
            interactor.setOnlineLobbyEnabled(connected);
            // ask for games to resume
            requestGames();
        }
        else
        {
            interactor.setOnlineLobbyEnabled(connected);
            interactor.setResumeGameEnabled(connected);
            // try reconnect
            //client.connect();
        }
    }

    public void resumeGame()
    {
        if (games == null || games.size() == 0)
        {
            Logger.w("No games to resume");
            return;
        }

        client.sendDicyMessage(new ResumeGameMessage(games.get(0).getId()));
    }
}
