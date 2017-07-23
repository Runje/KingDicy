package com.example.Communication;

import com.example.Communication.Messages.AskForusernameMessage;
import com.example.Communication.Messages.ChallengePlayerReqMessage;
import com.example.Communication.Messages.ChallengePlayerResMessage;
import com.example.Communication.Messages.ChatMessage;
import com.example.Communication.Messages.SendUsernameMessage;
import com.example.Communication.Messages.StartGameMessage;
import com.example.Communication.Messages.UserListReqMessage;
import com.example.Communication.Messages.UserListResMessage;
import com.example.Model.OnlineLobby;
import com.example.Model.User;
import com.example.OldModel.Logger;
import com.example.OnConnectionChangedListener;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Thomas on 02.04.2016.
 */
public class OnlineLobbyConnection implements ServerConnection, DicyMessageReceivedListener, OnConnectionChangedListener
{

    private final DicyClient client;
    private OnlineLobby lobby;

    private String id;
    private LobbyConnectionInteractor lobbyConnectionInteractor;

    public OnlineLobbyConnection(String id, DicyClient client, LobbyConnectionInteractor interactor)
    {
        this.id = id;
        this.lobbyConnectionInteractor = interactor;
        this.client = client;
        this.lobby = new OnlineLobby();
    }

    @Override
    public void stop()
    {
        client.stop();
    }

    @Override
    public void start()
    {
        client.setDicyMessageReceivedListener(this);
        Logger.i("Add ConnectionListener from OnlineLobbyConnection");
        client.addOnConnectionChangedListener(this);
        client.start();
        if (client.isConnected())
        {
            requestOnlinePlayer();
        }

        lobbyConnectionInteractor.showConnectionStatus(client.isConnected());
    }

    @Override
    public void onReceivedDicyMessage(DicyMessage msg)
    {
        Logger.i("msg received(online lobby): " + msg.getName());
        switch(msg.getName())
        {
            case AskForusernameMessage.Name:
                lobbyConnectionInteractor.askForUsername();
                break;
            case UserListResMessage.Name:
                Logger.i("Received users");
                UserListResMessage userListResMessage = (UserListResMessage) msg;
                List<User> users = userListResMessage.getUsers();
                Logger.i("Received users: " + Arrays.toString(users.toArray()));
                lobby.setUsers(users);

                lobbyConnectionInteractor.setUsersInLobby(lobby.getUsersAsList());
                break;
            case ChatMessage.Name:
                ChatMessage chatMessage = (ChatMessage) msg;
                lobby.addChat(lobby.getUser(chatMessage.getAuthorId()).toString() + ": " + chatMessage.getChat());
                lobbyConnectionInteractor.setChat(lobby.getChat());
                break;

            case ChallengePlayerReqMessage.Name:
                ChallengePlayerReqMessage challengePlayerReqMessage = (ChallengePlayerReqMessage) msg;
                lobbyConnectionInteractor.challengeFromPlayer(lobby.getUser(challengePlayerReqMessage.getChallengerId()));
                break;

            case ChallengePlayerResMessage.Name:
                ChallengePlayerResMessage challengePlayerResMessage = (ChallengePlayerResMessage) msg;
                lobbyConnectionInteractor.answerChallengeFromPlayer(lobby.getUser(challengePlayerResMessage.getChallengerId()), challengePlayerResMessage.isAccepted());
                break;

            case StartGameMessage.Name:
                StartGameMessage startGameMessage = (StartGameMessage) msg;
                // TODO: players
                lobbyConnectionInteractor.startGame(startGameMessage.getGame());
                break;
            default:
                Logger.w("Unknown message in OnlineLobby received: " + msg.getName());
        }
    }

    public void setUsername(String name)
    {
        DicyMessage msg = new SendUsernameMessage(name);
        client.sendDicyMessage(msg);
    }

    @Override
    public void onConnectionChanged(boolean connected)
    {
        Logger.i("Connection changed in OnlineLobby: " + connected);
        if (!connected)
        {
            // try to reconnect
            //client.connect();
        }
        else
        {
            requestOnlinePlayer();
        }

        lobbyConnectionInteractor.showConnectionStatus(connected);
    }

    private void requestOnlinePlayer()
    {
        client.sendDicyMessage(new UserListReqMessage());
    }

    public void sendChat(String chat)
    {
        // TODO: check for length and validity(äöü can not be sent)
        client.sendDicyMessage(new ChatMessage(chat, id));
    }

    public void sendChallenge(String playerId)
    {
        client.sendDicyMessage(new ChallengePlayerReqMessage(id, playerId));
    }

    public void sendAnswerChallenge(User player, boolean accepted)
    {
        client.sendDicyMessage(new ChallengePlayerResMessage(player.getId(), id, accepted));
    }

    public void removeListener()
    {
        client.removeOnConnectionChangedListener(this);
    }
}
