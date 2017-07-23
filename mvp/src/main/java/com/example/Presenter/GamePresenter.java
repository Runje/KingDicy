package com.example.Presenter;

import com.example.View.GameView;
import com.example.OldModel.Coords;

/**
 * Created by Thomas on 12.03.2016.
 */
public interface GamePresenter
{
    void reqSwitchDices(Coords first, Coords second);

    void switchExecuted(Coords first, Coords second);

    void detachView();

    void attachView(GameView view);

    void switchbackExecuted(Coords first, Coords second);

    void reqSync();
}
