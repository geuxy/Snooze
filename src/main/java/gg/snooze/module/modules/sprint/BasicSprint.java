package gg.snooze.module.modules.sprint;

import gg.snooze.event.Listener;
import gg.snooze.event.events.PreUpdateEvent;
import gg.snooze.module.mode.BaseSubModule;
import gg.snooze.module.modules.SprintModule;

public class BasicSprint extends BaseSubModule<SprintModule> {

    @Override
    public void onEnable() {
        this.module.addListener(PreUpdateEvent.ID, onUpdate);
    }

    @Override
    public void onDisable() {
        this.module.removeListener(PreUpdateEvent.ID, onUpdate);
    }

    private final Listener<PreUpdateEvent> onUpdate = event -> {
        System.out.println("This is Basic mode");
        // do basic sprint stuff
    };

}
