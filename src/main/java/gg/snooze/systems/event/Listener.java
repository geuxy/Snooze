package gg.snooze.systems.event;

import gg.snooze.systems.event.callables.AbstractEvent;

public interface Listener<T extends AbstractEvent> {

    void onEvent(T event);

}
