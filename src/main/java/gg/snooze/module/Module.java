package gg.snooze.module;

import gg.snooze.Snooze;
import gg.snooze.module.info.ModuleConfig;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleMetadata;
import gg.snooze.setting.SettingOwner;
import gg.snooze.util.MinecraftInstance;
import gg.snooze.util.exceptions.ModuleToggleException;
import gg.snooze.setting.Setting;

import java.util.Map;

public class Module implements SettingOwner, MinecraftInstance {

    private int[] listeners;

    public final ModuleMetadata metadata;
    public final ModuleConfig config;

    public Module() {
        ModuleData data = this.getClass().getAnnotation(ModuleData.class);

        this.listeners = data.events();
        this.metadata = new ModuleMetadata(data.name(), data.note(), data.type());
        this.config = new ModuleConfig(data.enabled(), data.keyCode());
    }

    @Override
    public void addSetting(Setting setting) {
        this.config.settings.put(setting.name, setting);
    }

    public String getSuffix() {
        return null;
    }

    public Map<String, Setting> getSettings() {
        return this.config.settings;
    }

    public void onEnable() {}
    public void onDisable() {}

    public void toggle() {
        if(this.config.enabled) {
            this.disable();

        } else {
            this.enable();
        }
    }

    public void enable() {
        try {
            if (!this.config.enabled) {
                this.onEnable();
                Snooze.INSTANCE.eventBus.subscribe(this, this.listeners);
                this.config.enabled = true;
            }
        } catch(ModuleToggleException _) {
        }
    }

    public void disable() {
        try {
            if (this.config.enabled) {
                this.onDisable();
                Snooze.INSTANCE.eventBus.unsubscribe(this, this.listeners);
                this.config.enabled = false;
            }
        } catch(ModuleToggleException _) {
        }
    }

}
