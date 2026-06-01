package gg.snooze.ui.framework.event;

public class EventInvoker {

    public void invoke(int eventId, BaseEvent event, Object... listeners) {
        var listener = ((Listener) listeners[eventId]);

        if(listener != null) {
            listener.onEvent(event);
        }
    }

}
