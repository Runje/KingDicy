package com.example.Presenter;

import com.example.Communication.DicyClient;
import com.example.Model.DicyGame;
import com.example.Model.StartPage;
import com.example.Model.StartPageInteractor;
import com.example.OldModel.Logger;
import com.example.View.StartPageView;

/**
 * Created by Thomas on 17.04.2016.
 */
public class StartPagePresenterImpl implements StartPagePresenter, StartPageInteractor
{
    StartPageView view;
    StartPage model;

    public StartPagePresenterImpl(StartPageView view, DicyClient client, String playerId)
    {
        this.view = view;
        model = new StartPage(this, client, playerId);
    }

    @Override
    public void setOnlineLobbyEnabled(boolean enabled)
    {
        view.setOnlineLobbyEnabled(enabled);
    }

    @Override
    public void setResumeGameEnabled(boolean enabled)
    {
        view.setResumeGameEnabled(enabled);
    }

    @Override
    public void startGame(DicyGame game)
    {
        view.startGame(game);
    }


    @Override
    public void attachView(StartPageView view)
    {
        this.view = view;
        model.start();
    }

    @Override
    public void detachView()
    {
        this.view = new NullStartPageView();
        model.stop();
    }

    @Override
    public void resumeGame()
    {
        model.resumeGame();
    }
}
