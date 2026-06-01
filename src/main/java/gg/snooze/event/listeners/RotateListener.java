package gg.snooze.event.listeners;

import gg.snooze.event.events.RotateEvent;

@FunctionalInterface
public interface RotateListener {

    void onRotate(RotateEvent event);

}
