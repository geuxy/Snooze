package gg.snooze.module;

import gg.snooze.Snooze;
import gg.snooze.event.Listener;
import gg.snooze.module.info.ModuleConfig;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleMetadata;
import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;
import java.util.function.BooleanSupplier;

@Getter @Setter
public class Module implements ValueOwner {

    private static final BooleanSupplier DEFAULT_TOGGLE_SUPPLIER = () -> true;

    private final Int2ObjectArrayMap<Listener<?>> listeners;

    private final ModuleMetadata metadata;
    private final ModuleConfig config;

    private BooleanSupplier onEnable = DEFAULT_TOGGLE_SUPPLIER;
    private BooleanSupplier onDisable = DEFAULT_TOGGLE_SUPPLIER;

    public Module() {
        ModuleData data = this.getClass().getAnnotation(ModuleData.class);

        this.listeners = new Int2ObjectArrayMap<>();
        this.metadata = new ModuleMetadata(data.name(), data.note(), data.type());
        this.config = new ModuleConfig(data.enabled(), data.keyCode());

        Snooze.INSTANCE.modules.setAddingModule(this);
    }

    @Override
    public LinkedHashMap<String, BaseValue<?>> getProperties() {
        return this.config.getProperties();
    }

    public void setEnabled(boolean enabled) {
        if(enabled == this.config.isEnabled()) {
            return;
        }

        if(enabled) {
            if(this.onEnable.getAsBoolean()) {
                this.config.setEnabled(true);
                this.listeners.forEach(Snooze.INSTANCE.eventBus::subscribe);
            }
        } else {
            if(this.onDisable.getAsBoolean()) {
                this.listeners.forEach(Snooze.INSTANCE.eventBus::unsubscribe);
                this.config.setEnabled(false);
            }
        }
    }

}
