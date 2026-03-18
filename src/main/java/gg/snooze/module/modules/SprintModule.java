package gg.snooze.module.modules;

import gg.snooze.event.events.UpdateEvent;
import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;

import gg.snooze.value.ValueFactory;

@ModuleData(
        name = "Sprint",
        note = "Makes the player sprint automatically",
        type = ModuleType.MOVEMENT
)
public class SprintModule extends Module {

    public SprintModule() {
        var omni = ValueFactory.bool("All Directions");

        this.getListeners().put(UpdateEvent.ID, event -> {
            if(omni.getValue()) {
                // do omni

            } else {
                // do legit
            }
        });
    }

}
