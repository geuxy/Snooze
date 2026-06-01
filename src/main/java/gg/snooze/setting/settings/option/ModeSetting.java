package gg.snooze.setting.settings.option;

import gg.snooze.setting.SettingOwner;
import gg.snooze.setting.settings.BaseOptionSetting;

import java.util.function.BiConsumer;

public final class ModeSetting<T> extends BaseOptionSetting<T, ModeSetting<T>> {

    private BiConsumer<Option<T>, Option<T>> action;
    private int selectedIndex;

    public ModeSetting(String name, SettingOwner owner) {
        super(name, owner);
    }

    @Override
    public boolean isSelected(int index) {
        return this.selectedIndex == index;
    }

    @Override
    public void select(int index) {
        if(index < 0 || index >= this.options.size()) {
            return;
        }

        if(action != null) {
            this.action.accept(this.options.get(this.selectedIndex), this.options.get(index));
        }

        this.selectedIndex = index;
    }

    public T getValue() {
        return this.options.get(this.selectedIndex).getValue();
    }

    public ModeSetting<T> setAction(BiConsumer<Option<T>, Option<T>> action) {
        this.action = action;
        return this;
    }

    public int getSelectedIndex() {
        return selectedIndex;
    }

}
