package gg.snooze.event.events;

import gg.snooze.event.Event;
import gg.snooze.event.listeners.RenderHudListener;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public record RenderHudEvent(GuiGraphicsExtractor graphics, DeltaTracker deltaTracker) implements Event<RenderHudListener> {

    public static final int ID = 3;

    @Override
    public void invoke(RenderHudListener listener) {
        listener.onRenderHud(this);
    }

}
