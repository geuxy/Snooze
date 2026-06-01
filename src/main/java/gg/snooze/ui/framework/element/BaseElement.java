package gg.snooze.ui.framework.element;

import gg.snooze.ui.framework.event.BaseEvent;
import gg.snooze.ui.framework.event.Listener;
import gg.snooze.ui.framework.event.system.ListenerSystem;
import gg.snooze.ui.framework.event.system.ListenerSystemImpl;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

public abstract class BaseElement {

    private final ListenerSystem listenerSystem = new ListenerSystemImpl(8, 32);

    public int x, y, width, height, preferredWidth, preferredHeight;

    public abstract void render(@NotNull ElementStyle style);

    public <T extends BaseEvent> void invoke(int eventId, T event) {
        this.listenerSystem.invoke(eventId, event);
    }

    public <T extends BaseEvent> void listen(int eventId, Listener<T> listener) {
        this.listenerSystem.addListener(eventId, listener);
    }

    public void setSize(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public void setPreferredSize(int w, int h) {
        this.preferredWidth = w;
        this.preferredHeight = h;
    }

    public boolean isMouseAt(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public boolean isMouseAt(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

}
