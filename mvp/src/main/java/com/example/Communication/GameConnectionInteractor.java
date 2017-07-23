package com.example.Communication;

import com.example.Model.DicyGame;
import com.example.Model.User;
import com.example.OldModel.Board;
import com.example.OldModel.Coords;
import com.example.OldModel.Rules;

import java.util.List;

/**
 * Created by Thomas on 02.04.2016.
 */
public interface GameConnectionInteractor
{
    void showConnectionStatus(boolean connected);

    void setChat(List<String> chat);

    void switchDices(Coords first, Coords second);

    void switchbackDices(Coords first, Coords second);

    void syncGame(DicyGame game);

    void next();
}
