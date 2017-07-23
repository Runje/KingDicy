package com.example.thomas.dicyplayground.Dagger;

import android.app.Application;
import android.content.Context;

import com.example.Communication.DicyClient;
import com.example.OldModel.Logger;
import com.example.thomas.dicyplayground.DicyApp;
import com.example.thomas.dicyplayground.GameActivity;
import com.example.thomas.dicyplayground.util.Installation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Thomas on 26.03.2016.
 */
@Module
public class CommunicationModule
{
    private final DicyApp app;

    public CommunicationModule(DicyApp app)
    {
        this.app = app;
    }

    @Provides
    @Singleton
    DicyClient provideDicyClient(String id)
    {
        Logger.i("Creating Dicy Client with Dagger, id: " + id);
        return new DicyClient(id);
    }

    @Provides
    String provideId(Context context)
    {
        Logger.i("Providing id");
        return Installation.id(context);
    }

    @Provides
    @Singleton
    Context provideApplicationContext()
    {
        return app;
    }

    @Provides
    @Singleton
    Application provideApplication()
    {
        Logger.i("Providing app");
        return app;
    }
}
