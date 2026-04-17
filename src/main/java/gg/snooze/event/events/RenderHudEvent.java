package gg.snooze.event.events;

import gg.snooze.event.callables.BaseEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

@Getter @RequiredArgsConstructor
public class RenderHudEvent extends BaseEvent {

    public static final int ID = 3;

    private final GuiGraphicsExtractor graphics;
    private final DeltaTracker deltaTracker;

}
