package gg.snooze.event;

import gg.snooze.event.callables.AbstractEvent;

public class EventBus {

    private final Object[] data;
    private final int[] listenerCounts;

    private final int maxEvents, maxListeners;

    public EventBus(int maxEvents, int maxListeners) {
        this.maxEvents = maxEvents;
        this.maxListeners = maxListeners;

        this.data = new Object[maxEvents * maxListeners];
        this.listenerCounts = new int[maxListeners];
    }

    public void subscribe(int eventId, Object listener) {
        int count = listenerCounts[eventId];
        int index = eventId * maxListeners + count;

        this.data[index] = listener;
        this.listenerCounts[eventId] = count + 1;
    }

    public void unsubscribe(int eventId, Object listener) {
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
    public void postUnsafe(int eventId, AbstractEvent event) {
        int count = this.listenerCounts[eventId];
        int baseIndex = eventId * maxListeners;

        for (int i = 0; i < count; i++) {
            event.call(data[baseIndex + i]);
        }
    }

}
