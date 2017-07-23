package com.example.Model;

/**
 * Created by Thomas on 16.04.2016.
 */
public interface StartPageInteractor
{
    void setOnlineLobbyEnabled(boolean enabled);

    void setResumeGameEnabled(boolean enabled);

    void startGame(DicyGame game);
}
