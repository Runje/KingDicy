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
public interface OnlineLobbyView
{
    void askForUsername();

    void setUsers(List<User> users);

    void showConnectionStatus(boolean connected);

    void setChat(List<String> chat);

    void deleteSendedChat();

    void challengeFromPlayer(User player);

    void startGame(DicyGame game);
}
