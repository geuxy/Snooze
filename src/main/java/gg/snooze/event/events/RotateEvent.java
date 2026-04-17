package gg.snooze.event.events;

import gg.snooze.event.callables.BaseEvent;
import gg.snooze.util.Rotation;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RotateEvent extends BaseEvent {

    public static final int ID = 2;

    public final Rotation rotation;

}
