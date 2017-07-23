package com.example.View;

import com.example.Model.DicyGame;
import com.example.Model.User;
import com.example.OldModel.Board;
import com.example.OldModel.Rules;

import java.util.Collection;
import java.util.List;

/**
 * Created by Thomas on 03.04.2016.
 */
public class NullOnlineLobbyView implements OnlineLobbyView
{
    @Override
    public void askForUsername()
    {

    }

    @Override
    public void setUsers(List<User> users)
    {

    }

    @Override
    public void showConnectionStatus(boolean connected)
    {

    }

    @Override
    public void setChat(List<String> chat)
    {

    }

    @Override
    public void deleteSendedChat()
    {

    }

    @Override
    public void challengeFromPlayer(User player)
    {

    }

    @Override
    public void startGame(DicyGame game)
    {

    }
}
