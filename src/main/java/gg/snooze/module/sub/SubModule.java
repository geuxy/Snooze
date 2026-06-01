package gg.snooze.module.sub;

import gg.snooze.util.exceptions.ModuleToggleException;

public interface SubModule {

    int[] EMPTY_EVENT_IDS = new int[0];

    void onEnable() throws ModuleToggleException;
    void onDisable() throws ModuleToggleException;

    default int[] getEvents() {
        return EMPTY_EVENT_IDS;
    }

}
