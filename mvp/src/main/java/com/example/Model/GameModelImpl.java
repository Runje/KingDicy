package com.example.Model;

import com.example.OldModel.Board;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;
import com.example.RxBus.Event;
import com.example.RxBus.EventBus;
import com.example.RxBus.Events.SwitchEvent;

import java.util.HashMap;
import java.util.Map;

import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Thomas on 12.03.2016.
 */
public class GameModelImpl implements GameModel
{
    private final ModelInteractor interactor;

    @Override
    public DicyGame getGame()
    {
        return game;
    }

    private final DicyGame game;
    private final EventBus bus;
    private final Map<MoveType, Boolean> enabledMap;

    public GameModelImpl(ModelInteractor interactor, EventBus bus, DicyGame game)
    {
        // TODO: Save game on anker points and make it possible to resume from there
        this.bus = bus;
        this.game = game;
        CompositeSubscription subscription = new CompositeSubscription();
        subscription.add(bus.toObservable().subscribe(new Action1<Event>()
        {
            @Override
            public void call(Event event)
            {
                switch(event.getName())
                {
                    case SwitchEvent.Name:
                        SwitchEvent executeSwitch = (SwitchEvent) event;
                        Coords first = executeSwitch.getFirst();
                        Coords second = executeSwitch.getSecond();
                        String requesterId = executeSwitch.getPlayerId();
                        switch (executeSwitch.getSwitchType())
                        {
                            case REQUEST_SWITCH:
                                reqSwitchDices(first, second, requesterId);
                                break;
                            case REQUEST_SWITCHBACK:
                                break;
                            case EXECUTE_SWITCH:
                                switchDices(first, second, requesterId);
                                break;
                            case EXECUTE_SWITCHBACK:
                                switchbackDices(first, second, requesterId);
                                break;
                        }

                        break;
                }
            }
        }));
        this.interactor = interactor;
        this.enabledMap = new HashMap<>();
        initEnabledHashmap();
        updateControls();

    }

    private void updateControls()
    {
        enabledMap.put(MoveType.SWITCH, game.playingPlayerPlaysOnThisDevice());
        interactor.setControlsEnabled(game.playingPlayerPlaysOnThisDevice());
    }

    private void initEnabledHashmap()
    {
        for (MoveType type : MoveType.values())
        {
            enabledMap.put(type, false);
        }
    }

    @Override
    public void reqSwitchDices(Coords first, Coords second, String playerId)
    {
        if (enabledMap.get(MoveType.SWITCH) && game.playerIsPlaying(playerId) && switchIsPossible(first, second))
        {
            setEnabledNormalControls(false);
            interactor.executeSwitch(first, second, playerId);
        }
        else
        {
            Logger.i("Switch denied: Switch Enabled: " + enabledMap.get(MoveType.SWITCH) + ", isPlaying: " + game.playerIsPlaying(playerId) + ", possible: " + switchIsPossible(first,second));
        }
    }

    private void setEnabledNormalControls(boolean enabled)
    {
        enabledMap.put(MoveType.SWITCH, enabled);
    }

    @Override
    public void switchDices(Coords first, Coords second, String playerId)
    {
        getBoard().switchElements(first, second);
        if (!switchIsSwitchback(first, second))
        {
            Logger.d("Switched %s with %s", first.toString(), second.toString());
            setEnabledNormalControls(true);
            //TODO: ONLY TEST CODE
            next();
        }
        else
        {
            Logger.d("Switchback");
            interactor.executeSwitchBack(first, second, playerId);
        }
    }

    private void next()
    {
        game.next();
        interactor.next();
        updateControls();
    }

    @Override
    public void switchbackDices(Coords first, Coords second, String playerId)
    {
        getBoard().switchElements(first, second);
        setEnabledNormalControls(true);
    }

    private boolean switchIsSwitchback(Coords first, Coords second)
    {
        // TODO: make test
        return false;
    }

    private boolean switchIsPossible(Coords first, Coords second)
    {
        return ! (coordsIsOutOfRange(first) || coordsIsOutOfRange(second));
    }

    private boolean coordsIsOutOfRange(Coords coords)
    {
        return coords.row < 0 || coords.row >= getBoard().getNumberOfRows() || coords.column < 0 || coords.column >= getBoard().getNumberOfColumns();
    }

    @Override
    public Board getBoard()
    {
        return game.getBoard();
    }
}
