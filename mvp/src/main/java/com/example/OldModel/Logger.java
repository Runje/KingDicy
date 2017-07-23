package com.example.OldModel;


import java.util.Objects;

import timber.log.Timber;

/**
 * Created by Thomas on 03.04.2015.
 */
public class Logger
{
    // TODO: Logger should detect right function
    private static LoggerType type = LoggerType.JAVA;

    public static void initForAndroid()
    {
        Timber.plant(new Timber.DebugTree());
        type = LoggerType.ANDROID;
    }

    private static void javaLog(String message, Object... args)
    {
        System.out.println(String.format(message, args));
    }

    public static void i(String message, Object... args)
    {
        switch (type)
        {
            case ANDROID:
                Timber.i(message, args);
                break;
            case JAVA:
                javaLog("INFO: " + message, args);
                break;
        }
    }

    public static void v(String message, Object... args)
    {
        switch (type)
        {
            case ANDROID:
                Timber.v(message, args);
                break;
            case JAVA:
                javaLog("VERBOSE: " + message, args);
                break;
        }

    }

    public static void d(String message, Object... args)
    {
        switch (type)
        {
            case ANDROID:
                Timber.d(message, args);
                break;
            case JAVA:
                javaLog("DEBUG: " + message, args);
                break;
        }

    }

    public static void e(String message, Object... args)
    {
        switch (type)
        {
            case ANDROID:
                Timber.e(message, args);
                break;
            case JAVA:
                javaLog("ERROR: " + message, args);
                break;
        }

    }
    public static void w(String message, Object... args)
    {
        switch (type)
        {
            case ANDROID:
                Timber.w(message, args);
                break;
            case JAVA:
                javaLog("WARN: " + message, args);
                break;
        }

    }


    private enum LoggerType { ANDROID, JAVA }
}
