package gg.snooze.module.modules;

import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;

import gg.snooze.module.modules.sprint.BasicSprint;
import gg.snooze.module.modules.sprint.LegitSprint;
import gg.snooze.module.sub.SubModule;
import gg.snooze.setting.SettingFactory;
import gg.snooze.setting.settings.*;
import gg.snooze.setting.settings.option.ModeSetting;

@ModuleData(
        name = "Sprint",
        note = "Sprints for the player automatically",
        type = ModuleType.MOVEMENT
)
public class SprintModule extends Module {

    private final ModeSetting<SubModule> mode = SettingFactory.<SubModule>mode("Mode")
            .addOption("Basic", new BasicSprint())
            .addOption("Legit", new LegitSprint())
            .setAction(BaseOptionSetting.subModuleAction(this));

    private final BooleanSetting myToggle = SettingFactory.bool("Omni");
    private final NumberSetting mySlider = SettingFactory.number("My Num", 0.0D, 100.0D, 1.0D);
    private final RangeSetting myRange = SettingFactory.range("My Range", 0.0D, 100.0D, 1.0D);

    @Override
    public void onEnable() {
        this.mode.getValue().enable();
    }

    @Override
    public void onDisable() {
        this.mode.getValue().disable();
    }

}
