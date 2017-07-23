package com.example.RxBus.Events;

import com.example.Model.SwitchType;
import com.example.OldModel.Coords;

/**
 * Created by Thomas on 13.03.2016.
 */
public class SwitchEvent extends SimpleEvent
{
    public SwitchType getSwitchType()
    {
        return switchType;
    }

    public final static String Name = "SWITCH";
    private Coords first;
    private Coords second;
    private String playerId;
    private SwitchType switchType;
    private SwitchEvent(SwitchType switchType, Coords first, Coords second, String playerId)
    {
        super(Name);
        this.switchType = switchType;
        this.first = first;
        this.second = second;
        this.playerId = playerId;
    }

    public static SwitchEvent Request(Coords first, Coords second, String playerId)
    {
        return new SwitchEvent(SwitchType.REQUEST_SWITCH, first, second, playerId);
    }

    public static SwitchEvent Execute(Coords first, Coords second, String playerId)
    {
        return new SwitchEvent(SwitchType.EXECUTE_SWITCH, first, second, playerId);
    }

    public static SwitchEvent ExecuteSwitchback(Coords first, Coords second, String playerId)
    {
        return new SwitchEvent(SwitchType.EXECUTE_SWITCHBACK, first, second, playerId);
    }

    public static SwitchEvent RequestSwitchback(Coords first, Coords second, String playerId)
    {
        return new SwitchEvent(SwitchType.REQUEST_SWITCHBACK, first, second, playerId);
    }

    public Coords getFirst()
    {
        return first;
    }

    public Coords getSecond()
    {
        return second;
    }

    public String getPlayerId()
    {
        return playerId;
    }
}
