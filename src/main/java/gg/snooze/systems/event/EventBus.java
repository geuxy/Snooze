package gg.snooze.systems.event;

import gg.snooze.systems.event.callables.AbstractEvent;

public class EventBus {

    private final Object[] data;
    private final int[] listenerCounts;

    private final int maxEvents;
    private final int maxListeners;

    public EventBus(int maxEvents, int maxListeners) {
        this.maxEvents = maxEvents;
        this.maxListeners = maxListeners;

        this.data = new Object[maxEvents * maxListeners];
        this.listenerCounts = new int[maxListeners];
    }

    public <T extends AbstractEvent> void subscribe(int eventId, Listener<T> listener) {
        int count = listenerCounts[eventId];
        int index = eventId * maxListeners + count;

        this.data[index] = listener;
        this.listenerCounts[eventId] = count + 1;
    }

    public <T extends AbstractEvent> void unsubscribe(int eventId, Listener<T> listener) {
        int count = this.listenerCounts[eventId];
        int baseIndex = eventId * maxListeners;

        for (int i = 0; i < count; i++) {
            int index = baseIndex + i;

            if (data[index] == listener) {
                int lastIndex = baseIndex + count - 1;

                this.data[index] = data[lastIndex];
                this.data[lastIndex] = null;
                this.listenerCounts[eventId] = count - 1;
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractEvent> void postUnsafe(int eventId, T event) {
        int count = this.listenerCounts[eventId];
        int baseIndex = eventId * maxListeners;

        for (int i = 0; i < count; i++) {
            ((Listener<T>) data[baseIndex + i]).onEvent(event);
        }
    }

}
