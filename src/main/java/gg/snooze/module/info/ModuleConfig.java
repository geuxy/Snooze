package gg.snooze.module.info;

import gg.snooze.setting.Setting;

import java.util.LinkedHashMap;
import java.util.Map;

public class ModuleConfig {

    public final Map<String, Setting> settings;

    public boolean enabled;
    public int keyCode;

    public ModuleConfig(boolean enabled, int keyCode) {
        this.settings = new LinkedHashMap<>();
        this.enabled = enabled;
        this.keyCode = keyCode;
    }

}
