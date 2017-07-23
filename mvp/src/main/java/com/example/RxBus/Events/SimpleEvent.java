package com.example.RxBus.Events;

import com.example.Model.SwitchType;
import com.example.RxBus.Event;

/**
 * Created by Thomas on 13.03.2016.
 */
public class SimpleEvent implements Event
{
    private String name;

    public SimpleEvent(String name)
    {
        this.name = name;
    }

    @Override
    public String getName()
    {
        return name;
    }
}
