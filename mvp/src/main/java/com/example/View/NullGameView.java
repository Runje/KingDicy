package com.example.View;

import com.example.Model.Player;
import com.example.OldModel.Board;
import com.example.OldModel.Coords;

/**
 * Created by Thomas on 02.04.2016.
 */
public class NullGameView implements OnlineGameView
{
    @Override
    public void showBoard(Board board)
    {

    }

    @Override
    public void switchDices(Coords first, Coords second)
    {

    }

    @Override
    public void switchDicesBack(Coords first, Coords second)
    {

    }

    @Override
    public void showPlayerNames(Player player1, Player player2)
    {

    }

    @Override
    public void showActivePlayer(int indexOfPlayingPlayer)
    {

    }

}
