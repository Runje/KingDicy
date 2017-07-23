package com.example.Presenter;

import com.example.Communication.DicyClient;
import com.example.Communication.LobbyConnectionInteractor;
import com.example.Communication.OnlineLobbyConnection;
import com.example.Model.DicyGame;
import com.example.Model.User;
import com.example.OldModel.Board;
import com.example.OldModel.Logger;
import com.example.OldModel.Rules;
import com.example.View.NullOnlineLobbyView;
import com.example.View.OnlineLobbyView;

import java.util.List;

/**
 * Created by Thomas on 03.04.2016.
 */
public class OnlineLobbyPresenterImpl implements OnlineLobbyPresenter, LobbyConnectionInteractor
{
    private OnlineLobbyView view;
    private final OnlineLobbyConnection connection;

    public OnlineLobbyPresenterImpl(OnlineLobbyView view, String id, DicyClient client)
    {
        this.view = view;
        this.connection = new OnlineLobbyConnection(id, client, this);
    }

    @Override
    public void askForUsername()
    {
        view.askForUsername();
    }

    @Override
    public void setUsersInLobby(List<User> users)
    {
        view.setUsers(users);
    }

    @Override
    public void showConnectionStatus(boolean connected)
    {
        view.showConnectionStatus(connected);
    }

    @Override
    public void setChat(List<String> chat)
    {
        view.setChat(chat);
    }

    @Override
    public void challengeFromPlayer(User player)
    {
        view.challengeFromPlayer(player);
    }

    @Override
    public void answerChallengeFromPlayer(User player, boolean accepted)
    {
        Logger.i("Challenge from " + player + " is " + (accepted ? "": "not") + "accepted");
    }

    @Override
    public void startGame(DicyGame game)
    {
        view.startGame(game);
    }

    @Override
    public void challengePlayer(String playerId)
    {
        connection.sendChallenge(playerId);
    }

    @Override
    public void answerChallenge(User player, boolean accepted)
    {
        connection.sendAnswerChallenge(player, accepted);
    }

    @Override
    public void setUsername(String name)
    {
        connection.setUsername(name);
    }

    @Override
    public void attachView(OnlineLobbyView view)
    {
        this.view = view;
        connection.start();
    }

    @Override
    public void detachView()
    {
        this.view = new NullOnlineLobbyView();
        connection.removeListener();
        connection.stop();
    }

    @Override
    public void sendChat(String chat)
    {
        connection.sendChat(chat);
        view.deleteSendedChat();
    }
}
