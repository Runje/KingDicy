package com.example.thomas.dicyplayground.Dagger;

import com.example.thomas.dicyplayground.DicyApp;
import com.example.thomas.dicyplayground.GameActivity;
import com.example.thomas.dicyplayground.OnlineLobbyActivity;
import com.example.thomas.dicyplayground.StartActivity;
import com.example.thomas.dicyplayground.StartGameActivity;

/**
 * Created by Thomas on 26.03.2016.
 */
public interface DaggerGraph
{
    void inject(GameActivity gameActivity);

    void inject(OnlineLobbyActivity onlineLobbyActivity);

    void inject(StartActivity startActivity);

    void inject(DicyApp dicyApp);

    void inject(StartGameActivity startGameActivity);
}
