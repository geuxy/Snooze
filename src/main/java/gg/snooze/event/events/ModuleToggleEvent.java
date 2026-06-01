package gg.snooze.event.events;

import gg.snooze.event.Event;
import gg.snooze.event.listeners.ModuleToggleListener;
import gg.snooze.module.Module;

public record ModuleToggleEvent(Module module) implements Event<ModuleToggleListener> {

    public static final int ID = 4;

    @Override
    public void invoke(ModuleToggleListener listener) {
        listener.onModuleToggle(this);
    }

}
