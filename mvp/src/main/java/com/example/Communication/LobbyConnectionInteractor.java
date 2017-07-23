package com.example.Communication;

import com.example.Model.DicyGame;
import com.example.Model.User;
import com.example.OldModel.Board;
import com.example.OldModel.Rules;

import java.util.Collection;
import java.util.List;

/**
 * Created by Thomas on 02.04.2016.
 */
public interface LobbyConnectionInteractor
{
    void askForUsername();

    void setUsersInLobby(List<User> users);

    void showConnectionStatus(boolean connected);

    void setChat(List<String> chat);

    void challengeFromPlayer(User player);

    void answerChallengeFromPlayer(User player, boolean accepted);

    void startGame(DicyGame game);
}
