package gg.snooze.systems.event.events;

import gg.snooze.systems.event.EventState;
import gg.snooze.systems.event.callables.AbstractEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class UpdateEvent extends AbstractEvent {

    public static final int ID = 1;

    private final EventState state;

}
