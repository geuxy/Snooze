package gg.snooze.event.events;

import gg.snooze.event.callables.BaseEvent;
import gg.snooze.module.Module;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class ModuleToggleEvent extends BaseEvent {

    public static final int ID = 4;

    private final Module module;

}
