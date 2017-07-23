package com.example.thomas.dicyplayground;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.Communication.DicyClient;
import com.example.Model.DicyGame;
import com.example.Model.Player;
import com.example.OldModel.Board;
import com.example.OldModel.Coords;
import com.example.OldModel.Logger;
import com.example.OldModel.Rules;
import com.example.Presenter.GamePresenter;
import com.example.Presenter.GamePresenterImpl;
import com.example.Presenter.OnlineGamePresenter;
import com.example.Presenter.OnlineGamePresenterImpl;
import com.example.View.OnlineGameView;
import com.example.thomas.dicyplayground.Views.AskForUsernameView;
import com.example.thomas.dicyplayground.Views.BoardView;
import com.example.thomas.dicyplayground.Views.ViewUtilities;
import com.example.thomas.dicyplayground.util.ActivityUtils;
import com.example.thomas.dicyplayground.util.Installation;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 */
public class GameActivity extends AppCompatActivity implements OnlineGameView
{
    @Bind(R.id.board)
    BoardView boardRep;

    @Bind(R.id.textPlayer1)
    TextView player1Name;

    @Bind(R.id.textPlayer2)
    TextView player2Name;

    private GamePresenter presenter;

    @Inject
    DicyClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        DicyApp.component().inject(this);

        Intent intent = getIntent();
        DicyGame game = ActivityUtils.getGameFromIntent(intent);
        boolean isOnline = ActivityUtils.getIsOnlineFromIntent(intent);
        // TODO: test normal presenter
        if (isOnline)
        {
            presenter = new OnlineGamePresenterImpl(this, Installation.id(this), game, client);
            presenter.reqSync();

            // TODO: disable controls until synced
        }
        else
        {
            presenter = new GamePresenterImpl(this, Installation.id(this), game);
        }

        Logger.i("START");

        boardRep.setEventListener(new BoardView.EventListener()
        {
            @Override
            public void onBottom(Coords from)
            {
                Logger.d("Bottom");
                presenter.reqSwitchDices(from, new Coords(from.row + 1, from.column));
            }

            @Override
            public void onRight(Coords position)
            {
                Logger.d("Right");
                presenter.reqSwitchDices(position, new Coords(position.row, position.column + 1));
            }

            @Override
            public void onLeft(Coords position)
            {
                Logger.d("Left");
                presenter.reqSwitchDices(position, new Coords(position.row, position.column - 1));
            }

            @Override
            public void onTop(Coords position)
            {
                Logger.d("Top");
                presenter.reqSwitchDices(position, new Coords(position.row - 1, position.column));
            }
        });

    }

    @Override
    public void showActivePlayer(int indexOfPlayingPlayer)
    {
        runOnUiThread(() ->
        {
            player1Name.setTextColor(indexOfPlayingPlayer == 0 ? Color.YELLOW : Color.BLACK);
            player2Name.setTextColor(indexOfPlayingPlayer == 1 ? Color.YELLOW : Color.BLACK);
            player1Name.invalidate();
            player2Name.invalidate();
        });
    }

    @Override
    public void showPlayerNames(Player player1, Player player2)
    {
        runOnUiThread(() ->
        {
            player1Name.setText(player1.getName());
            player2Name.setText(player2.getName());
            player1Name.invalidate();
            player2Name.invalidate();
        });
    }

    @Override
    public void showBoard(Board board)
    {
        runOnUiThread(() ->
        {
            boardRep.setElements(board.getElements());
            boardRep.setRows(board.getNumberOfRows());
            boardRep.setColumns(board.getNumberOfColumns());
            boardRep.reinit();
            boardRep.postInvalidate();
            Logger.d("Show Board");
        });
    }

    @Override
    public void switchDices(final Coords first, final Coords second)
    {
        runOnUiThread(() ->
        {
            Runnable afterSwitch = () -> presenter.switchExecuted(first, second);
            boardRep.animateSwitchDices(first, second, 500, afterSwitch);
        });
    }

    @Override
    public void switchDicesBack(Coords first, Coords second)
    {
        runOnUiThread(() ->
        {
            Runnable afterSwitch = () -> presenter.switchbackExecuted(first, second);
            boardRep.animateSwitchDices(first, second, 500, afterSwitch);
        });
    }

    @Override
    protected void onStop()
    {
        Logger.i("Stopping GameActivity");
        presenter.detachView();
        super.onStop();
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        presenter.attachView(this);
    }


    public void clickdummy(View view)
    {
        //showBoard(new Board(5));
    }
}
