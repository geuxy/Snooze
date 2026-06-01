package gg.snooze.ui.framework.event.system;

import gg.snooze.ui.framework.event.BaseEvent;
import gg.snooze.ui.framework.event.Listener;

public interface ListenerSystem {

    <T extends BaseEvent> void addListener(int eventId, Listener<T> listener);
    <T extends BaseEvent> void removeListener(int eventId, Listener<T> listener);
    <T extends BaseEvent> void invoke(int eventId, T event);

}
