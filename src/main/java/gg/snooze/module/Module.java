package gg.snooze.module;

import gg.snooze.Snooze;
import gg.snooze.event.Listener;
import gg.snooze.event.callables.BaseEvent;
import gg.snooze.event.events.PreUpdateEvent;
import gg.snooze.module.info.ModuleConfig;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleMetadata;
import gg.snooze.util.exceptions.ModuleToggleException;
import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;
import it.unimi.dsi.fastutil.ints.Int2ObjectArrayMap;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter @Setter
public class Module implements ValueOwner {

    private final Int2ObjectArrayMap<Listener<?>> listeners;

    private final ModuleMetadata metadata;
    private final ModuleConfig config;

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

    public void onEnable() {}
    public void onDisable() {}

    public void registerListeners() {
        this.listeners.forEach(Snooze.INSTANCE.eventBus::subscribe);
    }

    public void unregisterListeners() {
        this.listeners.forEach(Snooze.INSTANCE.eventBus::unsubscribe);
    }

    public void clearListeners() {
        this.unregisterListeners();
        this.listeners.clear();
    }

    public <T extends BaseEvent> void addListener(int id, Listener<T> listener) {
        this.listeners.put(id, listener);

        if(this.config.isEnabled()) {
            Snooze.INSTANCE.eventBus.subscribe(PreUpdateEvent.ID, listener);
        }
    }

    public void removeListener(int id, Listener<?> listener) {
        Snooze.INSTANCE.eventBus.unsubscribe(PreUpdateEvent.ID, listener);
        this.listeners.remove(id);
    }

    public void setEnabled(boolean enabled) {
        if(enabled == this.config.isEnabled()) {
            return;
        }

        try {
            if (enabled) {
                this.onEnable();
                this.config.setEnabled(true);
                this.registerListeners();

            } else {
                this.onDisable();
                this.unregisterListeners();
                this.config.setEnabled(false);
            }

        } catch(ModuleToggleException _) {}

    }

}
