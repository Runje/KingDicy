package com.example.View;

import com.example.Model.Player;
import com.example.OldModel.Board;
import com.example.OldModel.Coords;

/**
 * Created by Thomas on 12.03.2016.
 */
public interface GameView
{
    void showBoard(Board board);

    void switchDices(Coords first, Coords second);

    void switchDicesBack(Coords first, Coords second);

    void showPlayerNames(Player player1, Player player2);

    void showActivePlayer(int indexOfPlayingPlayer);
}
