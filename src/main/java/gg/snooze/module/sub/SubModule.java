package gg.snooze.module.sub;

import gg.snooze.Snooze;
import gg.snooze.util.exceptions.ModuleToggleException;

public interface SubModule {

    int[] EMPTY_EVENT_IDS = new int[0];

    void onEnable() throws ModuleToggleException;
    void onDisable() throws ModuleToggleException;

    default void enable() {
        if(getEvents() != EMPTY_EVENT_IDS) {
            Snooze.INSTANCE.eventBus.subscribe(this, getEvents());
        }

        this.onEnable();
    }

    default void disable() {
        if(getEvents() != EMPTY_EVENT_IDS) {
            Snooze.INSTANCE.eventBus.unsubscribe(this, getEvents());
        }

        this.onDisable();
    }

    default int[] getEvents() {
        return EMPTY_EVENT_IDS;
    }

}
