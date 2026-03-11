package gg.snooze.systems.module.modules;

import gg.snooze.core.Snooze;
import gg.snooze.systems.event.Listener;
import gg.snooze.systems.event.events.UpdateEvent;
import gg.snooze.systems.module.Module;
import gg.snooze.systems.module.info.ModuleData;
import gg.snooze.systems.module.info.ModuleType;
import static gg.snooze.systems.property.factory.PropertyFactory.*;
import gg.snooze.systems.property.properties.*;

import java.util.Map;

@ModuleData(
        name = "Sprint",
        note = "Makes the player sprint automatically",
        type = ModuleType.MOVEMENT
)
public class SprintModule extends Module {

    private static final String[] MODE_OPTIONS = { "First", "Second" };
    private static final Map<String, Boolean> MULTI_OPTIONS = Map.of("First", false, "Second", false);

    private final ModeProperty<String> myMode = mode("My Mode", MODE_OPTIONS, String::toString);

    private final MultiToggleProperty myMulti = multi("My Multi", MULTI_OPTIONS);

    private final ToggleProperty myToggle = toggle("My Toggle")
            .setVisible(() -> myMode.getValue().equals("First"));

    @Override
    public boolean onEnable() {
        Snooze.INSTANCE.eventBus().subscribe(UpdateEvent.ID, onUpdate);
        return super.onEnable();
    }

    @Override
    public boolean onDisable() {
        Snooze.INSTANCE.eventBus().unsubscribe(UpdateEvent.ID, onUpdate);
        return super.onDisable();
    }

    private final Listener<UpdateEvent> onUpdate = event -> {
        // god bypass
    };

}
