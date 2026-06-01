package gg.snooze.module.modules.sprint;

import gg.snooze.event.events.PreUpdateEvent;
import gg.snooze.event.listeners.PreUpdateListener;
import gg.snooze.module.sub.SubModule;
import gg.snooze.util.exceptions.ModuleToggleException;

public class BasicSprint implements SubModule, PreUpdateListener {

    @Override
    public int[] getEvents() {
        return new int[] {PreUpdateEvent.ID};
    }

    @Override
    public void onPreUpdate(PreUpdateEvent event) {
        System.out.println("This is Basic mode");
    }

    @Override
    public void onEnable() throws ModuleToggleException {
    }

    @Override
    public void onDisable() throws ModuleToggleException {
    }

}
