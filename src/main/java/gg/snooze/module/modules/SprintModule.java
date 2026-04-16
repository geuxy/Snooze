package gg.snooze.module.modules;

import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;

import gg.snooze.module.mode.BaseSubModule;
import gg.snooze.module.modules.sprint.LegitSprint;
import gg.snooze.module.modules.sprint.BasicSprint;
import gg.snooze.value.ValueFactory;
import gg.snooze.value.values.*;
import lombok.RequiredArgsConstructor;

@ModuleData(
        name = "Sprint",
        note = "Makes the player sprint automatically",
        type = ModuleType.MOVEMENT
)
public class SprintModule extends Module {

    private final ModeValue<Mode> mode = ValueFactory.mode("Mode", Mode.values(), m -> m.name)
            .addAction(ModeValue.createSegmentManager(e -> e.sub));

    private final MultiValue multi = ValueFactory.multi("Multi", "First", "Second", "Third");
    private final BoolValue omni = ValueFactory.bool("Omni");
    private final NumberValue number = ValueFactory.number("My Num", 0.0D, 100.0D, 1.0D);
    private final RangeValue range = ValueFactory.range("My Range", 0.0D, 100.0D, 1.0D);

    @RequiredArgsConstructor
    enum Mode {
        LEGIT("Legit", new LegitSprint()),
        BASIC("Basic", new BasicSprint());

        final String name;
        final BaseSubModule<SprintModule> sub;
    }

    @Override
    public void onEnable() {
        this.mode.getValue().sub.onEnable();
    }

    @Override
    public void onDisable() {
        this.mode.getValue().sub.onDisable();
    }

}
