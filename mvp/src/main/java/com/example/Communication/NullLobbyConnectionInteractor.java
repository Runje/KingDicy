package com.example.Communication;

import com.example.Model.DicyGame;
import com.example.Model.User;
import com.example.OldModel.Board;
import com.example.OldModel.Rules;

import java.util.List;

/**
 * Created by Thomas on 10.04.2016.
 */
public class NullLobbyConnectionInteractor implements LobbyConnectionInteractor
{
    @Override
    public void askForUsername()
    {

    }

    @Override
    public void setUsersInLobby(List<User> users)
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
    public void challengeFromPlayer(User player)
    {

    }

    @Override
    public void answerChallengeFromPlayer(User player, boolean accepted)
    {

    }

    @Override
    public void startGame(DicyGame game)
    {

    }
}
