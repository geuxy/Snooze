package gg.snooze.setting;

import gg.snooze.Snooze;
import gg.snooze.module.Module;
import gg.snooze.setting.settings.*;
import gg.snooze.setting.settings.option.ModeSetting;
import gg.snooze.setting.settings.option.MultiSetting;

public final class SettingFactory {

    private SettingFactory() {
    }

    private static Module module() {
        return Snooze.INSTANCE.modules.getAddingModule();
    }

    public static <E> ModeSetting<E> mode(String name) {
        return new ModeSetting<>(name, module());
    }

    public static <E> MultiSetting<E> multi(String name) {
        return new MultiSetting<>(name, module());
    }

    public static BooleanSetting bool(String name) {
        return new BooleanSetting(name, module());
    }

    public static NumberSetting number(String name, double min, double max, double step) {
        return new NumberSetting(name, module(), min, max, step);
    }

    public static RangeSetting range(String name, double min, double max, double step) {
        return new RangeSetting(name, module(), min, max, step);
    }

    public static LabelSetting label(String name) {
        return label(name, module());
    }

    public static <E> ModeSetting<E> mode(String name, SettingOwner owner) {
        return new ModeSetting<>(name, owner);
    }

    public static <E> MultiSetting<E> multi(String name, SettingOwner owner) {
        return new MultiSetting<>(name, owner);
    }

    public static BooleanSetting bool(String name, SettingOwner owner) {
        return new BooleanSetting(name, owner);
    }

    public static NumberSetting number(String name, SettingOwner owner, double min, double max, double step) {
        return new NumberSetting(name, owner, min, max, step);
    }

    public static RangeSetting range(String name, SettingOwner owner, double min, double max, double step) {
        return new RangeSetting(name, owner, min, max, step);
    }

    public static LabelSetting label(String name, SettingOwner owner) {
        return new LabelSetting(name, owner);
    }

}
