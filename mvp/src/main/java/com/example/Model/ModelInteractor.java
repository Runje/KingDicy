package com.example.Model;

import com.example.OldModel.Coords;

/**
 * Created by Thomas on 13.03.2016.
 */
public interface ModelInteractor
{
    void executeSwitch(Coords first, Coords second, String playerId);

    void setControlsEnabled(boolean enabled);

    void executeSwitchBack(Coords first, Coords second, String playerId);

    void next();
}
