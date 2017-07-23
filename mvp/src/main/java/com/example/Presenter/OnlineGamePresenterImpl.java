package com.example.Presenter;

import com.example.Communication.DicyClient;
import com.example.Communication.GameConnectionInteractor;
import com.example.Communication.LobbyConnectionInteractor;
import com.example.Communication.OnlineGameConnection;
import com.example.Communication.OnlineLobbyConnection;
import com.example.Model.DicyGame;
import com.example.Model.GameModelImpl;
import com.example.Model.User;
import com.example.OldModel.Board;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;
import com.example.OldModel.Rules;
import com.example.RxBus.EventBus;
import com.example.View.GameView;
import com.example.View.NullGameView;
import com.example.View.OnlineGameView;

import java.util.List;

/**
 * Created by Thomas on 02.04.2016.
 */
public class OnlineGamePresenterImpl extends GamePresenterImpl implements GameConnectionInteractor, OnlineGamePresenter
{
    @Override
    public void next()
    {
        model.getGame().next();
        super.next();
    }

    private final OnlineGameConnection connection;
    private OnlineGameView onlineView;

    @Override
    public void reqSwitchDices(Coords first, Coords second)
    {
        connection.reqSwitchDices(first, second);
    }

    @Override
    public void switchbackExecuted(Coords first, Coords second)
    {
        connection.switchbackExecuted(first, second);
    }

    @Override
    public void reqSync()
    {
        connection.reqSync();
    }


    @Override
    public void switchExecuted(Coords first, Coords second)
    {
        connection.switchExecuted(first, second);
    }

    public OnlineGamePresenterImpl(OnlineGameView view, String playerId, DicyGame game, DicyClient client)
    {
        super(view, playerId, game);
        this.onlineView = view;
        this.connection = new OnlineGameConnection(playerId, this, client, game.getId());
    }

    @Override
    public void detachView()
    {
        super.detachView();
        onlineView = new NullGameView();
        Logger.i("Stop connection");
        connection.stop();
    }

    @Override
    public void attachView(GameView view)
    {
        super.attachView(view);
        onlineView = (OnlineGameView) view;
        Logger.i("Start connection");
        connection.start();
    }

    @Override
    public void showConnectionStatus(boolean connected)
    {

    }

    @Override
    public void setChat(List<String> chat)
    {
        // TODO
    }

    @Override
    public void switchDices(Coords first, Coords second)
    {
        view.switchDices(first, second);
    }

    @Override
    public void switchbackDices(Coords first, Coords second)
    {
        view.switchDicesBack(first, second);
    }

    @Override
    public void syncGame(DicyGame game)
    {
        view.showBoard(game.getBoard());
    }

}
