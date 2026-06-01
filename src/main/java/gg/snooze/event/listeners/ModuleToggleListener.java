package gg.snooze.event.listeners;

import gg.snooze.event.events.ModuleToggleEvent;

@FunctionalInterface
public interface ModuleToggleListener {

    void onModuleToggle(ModuleToggleEvent event);

}
