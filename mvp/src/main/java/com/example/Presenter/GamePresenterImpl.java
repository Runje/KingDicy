package com.example.Presenter;

import com.example.Model.DicyGame;
import com.example.Model.GameModel;
import com.example.Model.GameModelImpl;
import com.example.Model.ModelInteractor;
import com.example.Model.Player;
import com.example.OldModel.Board;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;
import com.example.OldModel.Rules;
import com.example.RxBus.EventBus;
import com.example.RxBus.Events.SwitchEvent;
import com.example.View.GameView;
import com.example.View.NullGameView;

import java.util.List;

/**
 * Created by Thomas on 12.03.2016.
 */
public class GamePresenterImpl implements GamePresenter, ModelInteractor
{
    private final String playerId;
    GameModel model;
    GameView view;
    EventBus bus;

    public GamePresenterImpl(GameView view, String id, DicyGame game)
    {
        this.playerId = id;
        this.bus = new EventBus();
        this.model = new GameModelImpl(this, bus, game);
        this.view = view;
    }


    @Override
    public void reqSwitchDices(Coords first, Coords second)
    {
        bus.send(SwitchEvent.Request(first, second, model.getGame().getPlayingPlayer().getId()));
    }

    @Override
    public void switchExecuted(Coords first, Coords second)
    {
        bus.send(SwitchEvent.Execute(first, second, model.getGame().getPlayingPlayer().getId()));
    }

    @Override
    public void detachView()
    {
        Logger.i("Detaching view");
        this.view = new NullGameView();
    }

    @Override
    public void attachView(GameView view)
    {
        Logger.i("Attaching view");
        this.view = view;
        view.showBoard(model.getBoard());
        List<Player> players = model.getGame().getPlayers();
        view.showPlayerNames(players.get(0), players.get(1));
        view.showActivePlayer(model.getGame().getIndexOfPlayingPlayer());
    }

    @Override
    public void switchbackExecuted(Coords first, Coords second)
    {
        bus.send(SwitchEvent.ExecuteSwitchback(first, second, playerId));
    }

    @Override
    public void reqSync()
    {

    }

    @Override
    public void executeSwitch(Coords first, Coords second, String playerId)
    {
        view.switchDices(first, second);
    }

    @Override
    public void setControlsEnabled(boolean enabled)
    {
        // TODO: Brauche ich vielleicht gar nicht? Bei ReqSwitch nicht zulassen!?
    }

    @Override
    public void executeSwitchBack(Coords first, Coords second, String playerId)
    {
        view.switchDicesBack(first, second);
    }

    @Override
    public void next()
    {
        view.showActivePlayer(model.getGame().getIndexOfPlayingPlayer());
    }
}
