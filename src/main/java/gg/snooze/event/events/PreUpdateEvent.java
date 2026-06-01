package gg.snooze.event.events;

import gg.snooze.event.Event;
import gg.snooze.event.listeners.PreUpdateListener;

public record PreUpdateEvent() implements Event<PreUpdateListener> {

    public static final int ID = 1;

    @Override
    public void invoke(PreUpdateListener listener) {
        listener.onPreUpdate(this);
    }
}
