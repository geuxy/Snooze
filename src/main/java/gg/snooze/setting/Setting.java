package gg.snooze.setting;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BooleanSupplier;

public class Setting {

    private static final BooleanSupplier DEFAULT_VISIBILITY = () -> true;

    private final List<Setting> children = new ArrayList<>();

    public final String name;

    private BooleanSupplier visibility;

    public Setting(String name, SettingOwner owner) {
        this.name = name;
        this.visibility = DEFAULT_VISIBILITY;

        if(owner != null) {
            owner.addSetting(this);
        }
    }

    public boolean isVisible() {
        return this.visibility.getAsBoolean();
    }

    public boolean isParent() {
        return !this.children.isEmpty();
    }

    public Setting showIf(BooleanSupplier supplier) {
        this.visibility = supplier;
        return this;
    }

    public Setting childOf(Setting parent) {
        if(parent != this && !parent.children.contains(this) && !this.children.contains(parent)) {
            parent.children.add(this);
        }

        return this;
    }

}
