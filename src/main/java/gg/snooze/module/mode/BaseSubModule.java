package gg.snooze.module.mode;

import gg.snooze.Snooze;
import gg.snooze.module.Module;
import gg.snooze.util.exceptions.ModuleToggleException;

public abstract class BaseSubModule<T extends Module> {

    @SuppressWarnings("unchecked")
    public final T module = (T) Snooze.INSTANCE.modules.getAddingModule();

    public void onEnable() throws ModuleToggleException {}
    public void onDisable() throws ModuleToggleException {}

}
