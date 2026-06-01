package gg.snooze.module.info;

public enum ModuleType {

    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    RENDER("Render"),
    OTHER("Other");

    public final String name;

    ModuleType(String name) {
        this.name = name;
    }

}
