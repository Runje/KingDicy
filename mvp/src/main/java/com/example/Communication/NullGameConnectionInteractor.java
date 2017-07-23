package com.example.Communication;

import com.example.Model.DicyGame;
import com.example.OldModel.Coords;

import java.util.List;

/**
 * Created by Thomas on 10.04.2016.
 */
public class NullGameConnectionInteractor implements GameConnectionInteractor
{
    @Override
    public void showConnectionStatus(boolean connected)
    {

    }

    @Override
    public void setChat(List<String> chat)
    {

    }

    @Override
    public void switchDices(Coords first, Coords second)
    {

    }

    @Override
    public void switchbackDices(Coords first, Coords second)
    {

    }

    @Override
    public void syncGame(DicyGame game)
    {

    }

    @Override
    public void next()
    {

    }
}
