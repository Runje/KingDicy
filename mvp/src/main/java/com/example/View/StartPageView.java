package com.example.View;

import com.example.Model.DicyGame;

/**
 * Created by Thomas on 17.04.2016.
 */
public interface StartPageView
{
    void setOnlineLobbyEnabled(boolean enabled);

    void setResumeGameEnabled(boolean enabled);

    void startGame(DicyGame game);
}
