package com.example.Presenter;

import com.example.View.StartPageView;

/**
 * Created by Thomas on 16.04.2016.
 */
public interface StartPagePresenter
{
    void attachView(StartPageView view);

    void detachView();

    void resumeGame();
}
