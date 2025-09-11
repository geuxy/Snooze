package gg.snooze.systems.event.events;

import gg.snooze.systems.event.callables.AbstractEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class KeyPressedEvent extends AbstractEvent {

    public static final int ID = 0;

    private final int keyCode, action;

}
