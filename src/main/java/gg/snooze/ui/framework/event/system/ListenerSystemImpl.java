package gg.snooze.ui.framework.event.system;

import gg.snooze.ui.framework.event.BaseEvent;
import gg.snooze.ui.framework.event.Listener;

public class ListenerSystemImpl implements ListenerSystem {

    private final Listener<?>[][] data;
    private final int[] listenerCounts;

    public ListenerSystemImpl(int maxEvents, int maxListeners) {
        this.data = new Listener<?>[maxEvents][maxListeners];
        this.listenerCounts = new int[maxEvents];
    }

    @Override
    public <T extends BaseEvent> void addListener(int eventId, Listener<T> listener) {
        int count = this.listenerCounts[eventId];

        this.data[eventId][count] = listener;
        ++this.listenerCounts[eventId];
    }

    @Override
    public <T extends BaseEvent> void removeListener(int eventId, Listener<T> listener) {
        int count = this.listenerCounts[eventId];

        if(count == 0) {
            return;
        }

        Listener<?>[] listeners = this.data[eventId];

        for (int i = count; i >= 0; i--) {
            if (listeners[i] == listener) {
                int lastIndex = count - 1;

                listeners[i] = listeners[lastIndex];
                listeners[lastIndex] = null;
                --this.listenerCounts[eventId];
                break;
            }
        }
    }

    @Override @SuppressWarnings("unchecked")
    public <T extends BaseEvent> void invoke(int eventId, T event) {
        int count = this.listenerCounts[eventId];

        if(count == 0) {
            return;
        }

        Listener<?>[] listeners = this.data[eventId];

        for (int i = 0; i < count; i++) {
            ((Listener<T>) listeners[i]).onEvent(event);
        }
    }

}
