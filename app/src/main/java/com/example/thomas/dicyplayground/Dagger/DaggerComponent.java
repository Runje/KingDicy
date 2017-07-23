package com.example.thomas.dicyplayground.Dagger;

import com.example.thomas.dicyplayground.DicyApp;

import javax.inject.Singleton;

import dagger.*;

/**
 * Created by Thomas on 26.03.2016.
 */
@Singleton
@dagger.Component(modules = {CommunicationModule.class})
public interface DaggerComponent extends DaggerGraph
{
    static final class Initializer
    {
        public static DaggerComponent init(DicyApp app)
        {
            return DaggerDaggerComponent.builder().communicationModule(new CommunicationModule(app)).build();
        }
    }
}
