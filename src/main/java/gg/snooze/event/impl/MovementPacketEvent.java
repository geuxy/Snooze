package gg.snooze.event.impl;

import gg.snooze.event.callables.AbstractEvent;
import gg.snooze.event.listeners.MovementPacketListener;

public class MovementPacketEvent extends AbstractEvent<MovementPacketListener> {

    public static final int ID = 1;

    @Override
    public void call(MovementPacketListener listener) {
        listener.onMovementPacket();
    }

}
