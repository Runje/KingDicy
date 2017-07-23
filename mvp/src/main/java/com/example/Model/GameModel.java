package com.example.Model;

import com.example.OldModel.Board;
import com.example.OldModel.Coords;

/**
 * Created by Thomas on 12.03.2016.
 */
public interface GameModel
{
    void reqSwitchDices(Coords first, Coords second, String playerId);

    Board getBoard();

    void switchDices(Coords first, Coords second, String playerId);

    void switchbackDices(Coords first, Coords second, String playerId);

    DicyGame getGame();
}
