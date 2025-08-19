package gg.snooze.module;

import gg.snooze.core.Snooze;
import gg.snooze.module.info.ModuleConfig;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleMetadata;
import gg.snooze.property.Property;
import gg.snooze.property.PropertyOwner;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.Optional;

@Getter @Setter
public class Module implements PropertyOwner {

    private final ModuleMetadata metadata;
    private final ModuleConfig config;

    public Module() {
        ModuleData data = this.getClass().getAnnotation(ModuleData.class);

        this.metadata = new ModuleMetadata(data.name(), data.note(), data.type());
        this.config = new ModuleConfig(data.enabled(), data.keyCode());

        Snooze.INSTANCE.modules().setAddingModule(this);
    }

    @Override
    public LinkedHashMap<String, Property<?>> getProperties() {
        return this.config.getProperties();
    }

    public boolean onEnable() {
        return true;
    }

    public boolean onDisable() {
        return true;
    }

    public void setEnabled(boolean enabled) {
        if(enabled == this.config.isEnabled()) return;

        if(enabled) {
            if (onEnable()) this.config.setEnabled(true);

        } else {
            if(onDisable()) this.config.setEnabled(false);
        }
    }

}
