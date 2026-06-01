package gg.snooze.event;

public final class EventBus {

    private final Object[][] subscribers;
    private final int[] listenerCounts;

    public EventBus(int maxEvents, int maxListeners) {
        this.subscribers = new Object[maxEvents][maxListeners];
        this.listenerCounts = new int[maxEvents];
    }

    public void subscribe(Object listener, int... eventIds) {
        for(int eventId : eventIds) {
            subscribe(eventId, listener);
        }
    }

    public void subscribe(int eventId, Object listener) {
        var count = this.listenerCounts[eventId];
        var listeners = this.subscribers[eventId];

        if(count >= listeners.length) {
            throw new IllegalStateException("Max listeners reached for event: " + eventId);
        }

        listeners[count] = listener;
        this.listenerCounts[eventId]++;
    }

    public void unsubscribe(Object listener, int... eventIds) {
        for(int eventId : eventIds) {
            unsubscribe(eventId, listener);
        }
    }

    /*
     * TODO: Improve performance
     */
    public void unsubscribe(int eventId, Object listener) {
        int count = this.listenerCounts[eventId];

        if(count == 0) {
            return;
        }

        var listeners = this.subscribers[eventId];

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

    @SuppressWarnings({"unchecked", "rawtypes"})
    public void postUnsafe(int eventId, Event event) {
        int count = this.listenerCounts[eventId];

        if(count == 0) {
            return;
        }

        var listeners = this.subscribers[eventId];

        for(int i = 0; i < count; i++) {
            event.invoke(listeners[i]);
        }
    }

}