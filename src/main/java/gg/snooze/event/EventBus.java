package gg.snooze.event;

import gg.snooze.event.callables.BaseEvent;

public final class EventBus {

    private final Listener<?>[][] listeners;
    private final int[] listenerCounts;

    public EventBus(int maxEvents, int maxListeners) {
        this.listeners = new Listener<?>[maxEvents][maxListeners];
        this.listenerCounts = new int[maxEvents];
    }

    public <T extends BaseEvent> void subscribe(int eventId, Listener<T> listener) {
        var count = this.listenerCounts[eventId];
        var listeners = this.listeners[eventId];

        if(count >= listeners.length) {
            throw new IllegalStateException("Max listeners reached for event: " + eventId);
        }

        listeners[count] = listener;
        this.listenerCounts[eventId]++;
    }

    public <T extends BaseEvent> void unsubscribe(int eventId, Listener<T> listener) {
        int count = this.listenerCounts[eventId];

        if(count == 0) {
            return;
        }

        var listeners = this.listeners[eventId];

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

    @SuppressWarnings("unchecked")
    public <T extends BaseEvent> void postUnsafe(int eventId, T event) {
        int count = this.listenerCounts[eventId];

        if(count == 0) {
            return;
        }

        var listeners = this.listeners[eventId];

        for (int i = 0; i < count; i++) {
            ((Listener<T>) listeners[i]).onEvent(event);
        }
    }

}