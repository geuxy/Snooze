package gg.snooze.ui.framework.element;

import gg.snooze.ui.framework.event.BaseEvent;
import gg.snooze.ui.framework.event.EventInvoker;
import gg.snooze.ui.framework.event.Listener;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

public abstract class BaseElement {

    private final Object[] listeners = new Object[4];

    public int x, y, width, height, preferredWidth, preferredHeight;

    public abstract void render(@NotNull ElementStyle style);

    public void invoke(int eventId, BaseEvent event, EventInvoker eventInvoker) {
        eventInvoker.invoke(eventId, event, this.listeners);
    }

    public void listen(int eventId, Listener listener) {
        this.listeners[eventId] = listener;
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
