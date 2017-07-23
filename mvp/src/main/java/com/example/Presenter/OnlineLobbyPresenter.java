package com.example.Presenter;

import com.example.Model.User;
import com.example.View.OnlineLobbyView;

/**
 * Created by Thomas on 03.04.2016.
 */
public interface OnlineLobbyPresenter
{
    void setUsername(String name);

    void attachView(OnlineLobbyView view);

    void detachView();

    void sendChat(String chat);

    void challengePlayer(String playerId);

    void answerChallenge(User player, boolean accepted);
}
