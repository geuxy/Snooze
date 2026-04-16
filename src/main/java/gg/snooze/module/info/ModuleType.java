package gg.snooze.module.info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public enum ModuleType {

    COMBAT("Combat"),
    MOVEMENT("Movement"),
    PLAYER("Player"),
    VISUAL("Visual"),
    OTHER("Other");

    private final String name;
    private int moduleCount;

    public void increaseModulesCount() {
        this.moduleCount = Math.min(this.moduleCount + 1, Integer.MAX_VALUE);
    }

}
