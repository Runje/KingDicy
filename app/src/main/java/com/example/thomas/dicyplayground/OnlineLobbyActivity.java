package com.example.thomas.dicyplayground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.Communication.DicyClient;
import com.example.Model.DicyGame;
import com.example.Model.Player;
import com.example.Model.User;
import com.example.OldModel.Board;
import com.example.OldModel.Logger;
import com.example.OldModel.Rules;
import com.example.Presenter.OnlineLobbyPresenter;
import com.example.Presenter.OnlineLobbyPresenterImpl;
import com.example.thomas.dicyplayground.Dagger.DaggerGraph;
import com.example.thomas.dicyplayground.Views.ChallengePlayerView;
import com.example.View.OnlineLobbyView;
import com.example.thomas.dicyplayground.Views.AskForUsernameView;
import com.example.thomas.dicyplayground.Views.ChatView;
import com.example.thomas.dicyplayground.Views.UserListView;
import com.example.thomas.dicyplayground.util.ActivityUtils;
import com.example.thomas.dicyplayground.util.Installation;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

public class OnlineLobbyActivity extends AppCompatActivity implements OnlineLobbyView
{
    private OnlineLobbyPresenter presenter;

    private UserListView userListView;
    private ChatView chatView;
    @Bind(R.id.user_list)
    RecyclerView userList;

    @Bind(R.id.chat)
    RecyclerView chatList;

    @Bind(R.id.send_chat)
    ImageButton sendChat;

    @Bind(R.id.edit_chat)
    EditText editChat;

    @Bind(R.id.challenge_player)
    Button challengeButton;

    @Inject
    DicyClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Logger.i("Create Online Lobby View");
        setContentView(R.layout.online_lobby);
        ButterKnife.bind(this);
        userListView = new UserListView(userList, this);
        chatView = new ChatView(chatList, this);
        DicyApp.component().inject(this);
        this.presenter = new OnlineLobbyPresenterImpl(this, Installation.id(this), client);
    }

    @Override
    protected void onStop()
    {
        Logger.i("Detach Online Lobby View");
        presenter.detachView();
        super.onStop();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Logger.i("Attach Online Lobby View");
        presenter.attachView(this);
    }

    @Override
    public void askForUsername()
    {
        new AskForUsernameView(this, name -> presenter.setUsername(name)).show();
    }

    @Override
    public void setUsers(List<User> users)
    {
        runOnUiThread(() ->
        {
            for(User user : users)
            {
                Logger.i("Setting User: " + user);
            }
            userListView.setUsers(users);
            userListView.refresh();
        });
    }

    @Override
    public void showConnectionStatus(boolean connected)
    {
        challengeButton.setEnabled(connected);
    }

    @Override
    public void setChat(List<String> chat)
    {
        runOnUiThread(() ->
        {
            chatView.setChat(chat);
            chatView.refresh();
        });
    }

    @Override
    public void deleteSendedChat()
    {
        editChat.setText("");
    }

    @Override
    public void challengeFromPlayer(User player)
    {
        new ChallengePlayerView(this, player, accepted -> presenter.answerChallenge(player, accepted)).show();
    }

    @Override
    public void startGame(DicyGame game)
    {
        Logger.i("Starting game...");
        Intent intent = new Intent(this, GameActivity.class);

        ActivityUtils.putToIntent(intent, game);
        ActivityUtils.putIsOnlineToIntent(intent, true);
        startActivity(intent);
    }

    public void onClickSendChat(View view)
    {
        String chat = editChat.getText().toString();
        presenter.sendChat(chat);
    }

    public void onClickChallengePlayer(View view)
    {
        User user = userListView.getSelectedUser();
        if (user != null)
        {
            Logger.i("Selected user: " + user);
            presenter.challengePlayer(user.getId());
        }
    }

}
