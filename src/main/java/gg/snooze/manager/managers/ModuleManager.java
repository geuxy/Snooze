package gg.snooze.manager.managers;

import gg.snooze.Snooze;
import gg.snooze.event.events.KeyPressedEvent;
import gg.snooze.event.listeners.KeyPressedListener;
import gg.snooze.manager.MapManager;
import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleType;
import gg.snooze.module.modules.*;
import gg.snooze.module.modules.killaura.KillAuraModule;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ModuleManager extends MapManager<Class<? extends Module>, Module> implements KeyPressedListener {

    private Module addingModule;

    public void init() {
        this.putModules(
            KillAuraModule.class,

            SprintModule.class,

            ClickGuiModule.class,

            InterfaceModule.class
        );

        this.addingModule = null;

        Snooze.INSTANCE.eventBus.subscribe(KeyPressedEvent.ID, this);
    }

    public Module getAddingModule() {
        return addingModule;
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        for(Module module : this) {
            if(event.keyCode() == module.config.keyCode) {
                module.toggle();
            }
        }
    }

    @Nullable
    public <T extends Module> T getModule(Class<T> clazz) {
        for(Module module : this) {
            if(clazz.isInstance(module)) {
                return clazz.cast(module);
            }
        }
        return null;
    }

    public List<Module> getModules(ModuleType type) {
        List<Module> modules = new ArrayList<>();

        for(Module module : this) {
            if(module.metadata.type() != type) {
                continue;
            }

            modules.add(module);
        }

        return modules;
    }

    @SafeVarargs
    public final void putModules(Class<? extends Module>... classes) {
        Stream.of(classes).forEach(this::putModule);
    }

    public void putModule(Class<? extends Module> clazz) {
        try {
            Module module = (Module) clazz.getDeclaredConstructors()[0].newInstance();

            this.addingModule = module;
            this.put(clazz, module);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

}
