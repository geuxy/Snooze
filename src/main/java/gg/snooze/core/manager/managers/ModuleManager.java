package gg.snooze.core.manager.managers;

import gg.snooze.core.Snooze;
import gg.snooze.systems.event.Listener;
import gg.snooze.systems.event.events.KeyPressedEvent;
import gg.snooze.core.manager.MapManager;
import gg.snooze.systems.module.Module;
import gg.snooze.systems.module.modules.ClickGuiModule;
import gg.snooze.systems.module.modules.SprintModule;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.stream.Stream;

public class ModuleManager extends MapManager<Class<? extends Module>, Module> {

    @Getter @Setter
    private Module addingModule;

    public void init() {
        this.putModules(
            SprintModule.class,

            ClickGuiModule.class
        );

        this.addingModule = null;

        Snooze.INSTANCE.eventBus().subscribe(KeyPressedEvent.ID, onKeyPressed);
    }

    public void stop() {

    }

    private final Listener<KeyPressedEvent> onKeyPressed = event -> {
        for(Module module : this) {
            if(event.getKeyCode() == module.getConfig().getKeyCode()) {
                module.setEnabled(!module.getConfig().isEnabled());
            }
        }
    };

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
