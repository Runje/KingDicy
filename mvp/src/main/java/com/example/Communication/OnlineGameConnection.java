package com.example.Communication;

import com.example.Communication.Messages.GameCommandMessage;
import com.example.Communication.Messages.NextMessage;
import com.example.Communication.Messages.SwitchMessage;
import com.example.Communication.Messages.SyncGameMessage;
import com.example.Model.SwitchType;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;
import com.example.OnConnectionChangedListener;

/**
 * Created by Thomas on 02.04.2016.
 */
public class OnlineGameConnection implements ServerConnection, DicyMessageReceivedListener, OnConnectionChangedListener
{
    private final DicyClient client;

    private String playerId;
    private GameConnectionInteractor gameConnectionInteractor;
    private String gameId;
    private String requestedId;

    public OnlineGameConnection(String playerId, GameConnectionInteractor interactor, DicyClient client, String gameId)
    {
        this.gameId = gameId;
        this.playerId = playerId;
        this.gameConnectionInteractor = interactor;
        this.client = client;
        this.client.setDicyMessageReceivedListener(this);
        Logger.i("Add ConnectionListener from OnlineGameConnection");
        this.client.addOnConnectionChangedListener(this);
    }

    @Override
    public void stop()
    {
        client.stop();
    }

    @Override
    public void start()
    {
        client.start();
    }

    @Override
    public void onReceivedDicyMessage(DicyMessage msg)
    {
        Logger.i("msg received(OnlineGame): " + msg.getName());
        switch(msg.getName())
        {

            case SwitchMessage.Name:
                SwitchMessage executeSwitchMessage = (SwitchMessage) msg;
                switch (executeSwitchMessage.getSwitchType())
                {
                    case REQUEST_SWITCH:
                        break;
                    case REQUEST_SWITCHBACK:
                        gameConnectionInteractor.switchbackDices(executeSwitchMessage.getFirst(), executeSwitchMessage.getSecond());
                        break;
                    case EXECUTE_SWITCH:
                        requestedId = executeSwitchMessage.getRequesterId();
                        gameConnectionInteractor.switchDices(executeSwitchMessage.getFirst(), executeSwitchMessage.getSecond());
                        break;
                    case EXECUTE_SWITCHBACK:

                        break;
                }

                break;

            case SyncGameMessage.Name:
                SyncGameMessage syncGameMessage = (SyncGameMessage) msg;
                gameConnectionInteractor.syncGame(syncGameMessage.getGame());
                break;

            case NextMessage.Name:
                NextMessage nextMessage = (NextMessage) msg;
                gameConnectionInteractor.next();
                break;

            default:
                Logger.w("Unknown message in OnlineGameConnection received: " + msg.getName());
        }
    }

    @Override
    public void onConnectionChanged(boolean connected)
    {
        if (!connected)
        {
            // try to reconnect
            //client.connect();
        }
        else
        {
            gameConnectionInteractor.showConnectionStatus(connected);
        }
    }

    public void reqSwitchDices(Coords first, Coords second)
    {
        client.sendDicyMessage(new SwitchMessage(first, second, gameId, SwitchType.REQUEST_SWITCH, playerId));
    }

    public void switchExecuted(Coords first, Coords second)
    {
        client.sendDicyMessage(new SwitchMessage(first, second, gameId, SwitchType.EXECUTE_SWITCH, requestedId));

        // TODO:ready for next action
    }

    public void switchbackExecuted(Coords first, Coords second)
    {
        client.sendDicyMessage(new SwitchMessage(first, second, gameId, SwitchType.EXECUTE_SWITCHBACK, requestedId));
    }

    public void reqSync()
    {
        client.sendDicyMessage(GameCommandMessage.reqSync(gameId));
    }
}
