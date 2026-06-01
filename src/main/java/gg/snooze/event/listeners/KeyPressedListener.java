package gg.snooze.event.listeners;

import gg.snooze.event.events.KeyPressedEvent;

@FunctionalInterface
public interface KeyPressedListener {

    void onKeyPressed(KeyPressedEvent event);

}
