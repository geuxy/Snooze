package gg.snooze.setting.settings;

import gg.snooze.Snooze;
import gg.snooze.module.Module;
import gg.snooze.module.sub.SubModule;
import gg.snooze.setting.Setting;
import gg.snooze.setting.SettingOwner;
import gg.snooze.setting.settings.option.Option;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public abstract class BaseOptionSetting<T, E> extends Setting {

    protected final List<Option<T>> options;

    public static BiConsumer<Option<SubModule>, Option<SubModule>> subModuleAction(Module module) {
        return (o, n) -> {
            o.getValue().disable();
            Snooze.INSTANCE.eventBus.unsubscribe(o, o.getValue().getEvents());

            if(module.config.enabled) {
                n.getValue().enable();
                Snooze.INSTANCE.eventBus.subscribe(n, n.getValue().getEvents());
            }
        };
    }

    public BaseOptionSetting(String name, SettingOwner owner) {
        super(name, owner);
        this.options = new ArrayList<>();
    }

    public abstract boolean isSelected(int index);
    public abstract void select(int index);

    public E addOption(String name, T value) {
        this.options.add(new Option<>(name, value));
        return (E) this;
    }

}
