package gg.snooze.event.listeners;

import gg.snooze.event.events.RenderHudEvent;

@FunctionalInterface
public interface RenderHudListener {

    void onRenderHud(RenderHudEvent event);

}
