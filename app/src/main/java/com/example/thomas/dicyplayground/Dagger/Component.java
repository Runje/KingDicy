package com.example.thomas.dicyplayground.Dagger;

import com.example.Communication.DicyClient;

/**
 * Created by Thomas on 26.03.2016.
 */
public interface Component
{
    DicyClient provideDicyClient();
}
