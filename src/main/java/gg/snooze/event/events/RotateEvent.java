package gg.snooze.event.events;

import gg.snooze.event.Event;
import gg.snooze.event.listeners.RotateListener;
import gg.snooze.util.Rotation;

public record RotateEvent(Rotation rotation) implements Event<RotateListener> {

    public static final int ID = 2;

    @Override
    public void invoke(RotateListener listener) {
        listener.onRotate(this);
    }

}
