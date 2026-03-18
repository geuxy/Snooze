package gg.snooze.event;

import gg.snooze.event.callables.AbstractEvent;

public interface Listener<T extends AbstractEvent> {

    void onEvent(T event);

}
