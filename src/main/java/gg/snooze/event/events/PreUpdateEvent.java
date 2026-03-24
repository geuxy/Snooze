package gg.snooze.event.events;

import gg.snooze.event.callables.BaseEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class PreUpdateEvent extends BaseEvent {

    public static final int ID = 1;

}
