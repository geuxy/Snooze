package gg.snooze.setting.settings.option;

import gg.snooze.setting.SettingOwner;
import gg.snooze.setting.settings.BaseOptionSetting;

import java.util.function.BiConsumer;

public final class MultiSetting<T> extends BaseOptionSetting<T, MultiSetting<T>> {

    private BiConsumer<Option<T>, Boolean> action;
    private long mask;

    public MultiSetting(String name, SettingOwner owner) {
        super(name, owner);
        this.mask = 0L;
    }

    public MultiSetting<T> setAction(BiConsumer<Option<T>, Boolean> action) {
        this.action = action;
        return this;
    }

    @Override
    public boolean isSelected(int index) {
        return (this.mask & (1L << index)) != 0;
    }

    @Override
    public void select(int index) {
        this.mask ^= (1L << index);

        if(this.action != null) {
            this.action.accept(this.options.get(index), isSelected(index));
        }
    }

}
