package gg.snooze.event.events;

import gg.snooze.event.Event;
import gg.snooze.event.listeners.KeyPressedListener;

public record KeyPressedEvent(int keyCode, int action) implements Event<KeyPressedListener> {

    public static final int ID = 0;

    @Override
    public void invoke(KeyPressedListener listener) {
        listener.onKeyPressed(this);
    }

}
