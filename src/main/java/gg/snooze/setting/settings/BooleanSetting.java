package gg.snooze.setting.settings;

import gg.snooze.setting.Setting;
import gg.snooze.setting.SettingOwner;

public final class BooleanSetting extends Setting {

    private boolean value;

    public BooleanSetting(String name, SettingOwner owner) {
        super(name, owner);
    }

    public void toggle() {
        this.setValue(!this.value);
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        if(this.value != value) {
            this.value = value;
        }
    }

}
