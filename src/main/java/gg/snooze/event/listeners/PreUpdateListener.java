package gg.snooze.event.listeners;

import gg.snooze.event.events.PreUpdateEvent;

@FunctionalInterface
public interface PreUpdateListener {

    void onPreUpdate(PreUpdateEvent event);

}
