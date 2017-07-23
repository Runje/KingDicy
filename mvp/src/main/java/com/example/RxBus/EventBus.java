package com.example.RxBus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Thomas on 13.03.2016.
 */
public class EventBus implements Bus
{
    private final Subject<Event, Event> bus = new SerializedSubject<Event, Event>(PublishSubject.<Event>create());

    public void send(Event o)
    {
        bus.onNext(o);
    }

    public boolean hasObservers()
    {
        return bus.hasObservers();
    }

    public Observable<Event> toObservable()
    {
        return bus;
    }
}
