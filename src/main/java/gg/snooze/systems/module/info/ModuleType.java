package gg.snooze.systems.module.info;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public enum ModuleType {

    COMBAT("Combat"), MOVEMENT("Movement"), PLAYER("Player"), VISUAL("Visual"), OTHER("Other");

    private final String name;

}
