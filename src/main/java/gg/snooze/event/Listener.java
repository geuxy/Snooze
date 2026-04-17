package gg.snooze.event;

import gg.snooze.event.callables.BaseEvent;

@FunctionalInterface
public interface Listener<T extends BaseEvent> {

    void onEvent(T event);
    default int priority() {return PriorityConstants.NORMAL;}

}
