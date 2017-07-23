package com.example.thomas.dicyplayground;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.Communication.DicyClient;
import com.example.Model.DicyGame;
import com.example.Model.Player;
import com.example.Model.User;
import com.example.OldModel.Board;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;
import com.example.OldModel.RuleVariant;
import com.example.OldModel.Rules;
import com.example.Presenter.OnlineGamePresenter;
import com.example.Presenter.OnlineGamePresenterImpl;
import com.example.Presenter.StartPagePresenter;
import com.example.Presenter.StartPagePresenterImpl;
import com.example.View.OnlineGameView;
import com.example.View.StartPageView;
import com.example.thomas.dicyplayground.Service.RegistrationIntentService;
import com.example.thomas.dicyplayground.Views.BoardView;
import com.example.thomas.dicyplayground.util.ActivityUtils;
import com.example.thomas.dicyplayground.util.Installation;
import com.google.android.gms.common.GooglePlayServicesUtil;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class StartActivity extends AppCompatActivity implements StartPageView
{
    //TODO: Make DicyActivity with DicyClient
    @Inject
    DicyClient client;

    @Bind(R.id.button_resume)
    Button resumeButton;

    @Bind(R.id.button_offline)
    Button offlineButton;

    @Bind(R.id.button_onlinelobby)
    Button lobbyButton;

    StartPagePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Logger.i("On Create Start Activity");
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);
        DicyApp.component().inject(this);
        checkGooglePlayservices();
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
        presenter = new StartPagePresenterImpl(this, client, Installation.id(this));
    }

    private void checkGooglePlayservices()
    {
        int result = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        switch(result)
        {
            case 0:
                Log.i("StartPage", "Google play succeeded");
                break;
            default:
                Log.e("StartPage", "Error: " + result);
                GooglePlayServicesUtil.getErrorDialog(result, this, 7).show();
        }
    }

    public void click_resume(View v)
    {
        checkGooglePlayservices();
        // get online games
        presenter.resumeGame();
    }

    public void click_onlinelobby(View v)
    {
        startActivity(new Intent(this, OnlineLobbyActivity.class));
    }

    public void click_offline(View v)
    {
        Intent intent = new Intent(this, GameActivity.class);
        Board board = new Board(5);
        Rules rules = Rules.makeRules(RuleVariant.Atlantic_City);
        List<Player> players = Arrays.asList(new Player(new User(Installation.id(this), "Thomas"), true), new Player(new User("0", "Milena"), true));
        ActivityUtils.putToIntent(intent, new DicyGame(board, rules, players, 0, "2"));
        ActivityUtils.putIsOnlineToIntent(intent, false);
        startActivity(intent);
    }

    @Override
    protected void onStop()
    {
        Logger.i("Stop StartActivity");

        presenter.detachView();
        super.onStop();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        Logger.i("On Start Start Activity");
        presenter.attachView(this);
    }


    @Override
    public void setOnlineLobbyEnabled(boolean enabled)
    {
        runOnUiThread(() -> {
            lobbyButton.setEnabled(enabled);
            lobbyButton.postInvalidate();
        });

    }

    @Override
    public void setResumeGameEnabled(boolean enabled)
    {
        runOnUiThread(() -> {
            resumeButton.setEnabled(enabled);
            resumeButton.postInvalidate();
        });
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
}
