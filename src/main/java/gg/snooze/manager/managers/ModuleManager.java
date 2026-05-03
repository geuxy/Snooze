package gg.snooze.manager.managers;

import gg.snooze.Snooze;
import gg.snooze.event.Listener;
import gg.snooze.event.events.KeyPressedEvent;
import gg.snooze.manager.MapManager;
import gg.snooze.module.Module;
import gg.snooze.module.modules.ClickGuiModule;
import gg.snooze.module.modules.InterfaceModule;
import gg.snooze.module.modules.SprintModule;
import gg.snooze.module.modules.killaura.KillAuraModule;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class ModuleManager extends MapManager<Class<? extends Module>, Module> {

    @Getter @Setter
    private Module addingModule;

    public void init() {
        this.putModules(
            KillAuraModule.class,

            SprintModule.class,

            ClickGuiModule.class,

            InterfaceModule.class
        );

        this.addingModule = null;

        Snooze.INSTANCE.eventBus.subscribe(KeyPressedEvent.ID, onKeyPressed);
    }

    private final Listener<KeyPressedEvent> onKeyPressed = event -> {
        for(Module module : this) {
            if(event.getKeyCode() == module.getConfig().getKeyCode()) {
                module.setEnabled(!module.getConfig().isEnabled());
            }
        }
    };

    @Nullable
    public <T extends Module> T getModule(Class<T> clazz) {
        for(Module module : this) {
            if(clazz.isInstance(module)) {
                return clazz.cast(module);
            }
        }
        return null;
    }

    @SafeVarargs
    public final void putModules(Class<? extends Module>... classes) {
        Stream.of(classes).forEach(this::putModule);
    }

    public void putModule(Class<? extends Module> clazz) {
        try {
            this.put(clazz, (Module) clazz.getDeclaredConstructors()[0].newInstance());

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
