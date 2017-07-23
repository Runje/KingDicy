package com.example.thomas.dicyplayground;

import android.app.Application;

import com.example.Communication.DicyClient;
import com.example.OldModel.Logger;
import com.example.thomas.dicyplayground.Dagger.DaggerComponent;
import com.example.thomas.dicyplayground.Dagger.DaggerGraph;
import com.example.thomas.dicyplayground.util.Installation;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * Created by Thomas on 13.03.2016.
 */
public class DicyApp extends Application
{
    private static DicyApp instance;
    private static DaggerGraph graph;

    @Inject
    DicyClient client;

    @Override
    public void onCreate()
    {
        super.onCreate();
        instance = this;
        Logger.initForAndroid();
        Logger.i("Creating Dagger Graph");
        graph = DaggerComponent.Initializer.init(instance);
        Logger.i("Created Dagger Graph");
        graph.inject(this);
    }

    @Override
    public void onTerminate()
    {
        Logger.i("Terminating App");
        //client.disconnect();
        super.onTerminate();
    }

    public static DaggerGraph component()
    {
        return graph;
    }
}
