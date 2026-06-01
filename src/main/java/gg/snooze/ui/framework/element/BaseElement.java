package gg.snooze.ui.framework.element;

import gg.snooze.ui.framework.event.BaseEvent;
import gg.snooze.ui.framework.event.Listener;
import gg.snooze.ui.framework.event.system.ListenerSystem;
import gg.snooze.ui.framework.event.system.ListenerSystemImpl;
import gg.snooze.ui.framework.style.ElementStyle;

public abstract class BaseElement {

    private final ListenerSystem listenerSystem = new ListenerSystemImpl(8, 32);

    protected int x, y, width, height, preferredWidth, preferredHeight;

    private ElementStyle style;

    public abstract void render(int mouseX, int mouseY);

    public <T extends BaseEvent> void invoke(int eventId, T event) {
        this.listenerSystem.invoke(eventId, event);
    }

    public <T extends BaseEvent> void listen(int eventId, Listener<T> listener) {
        this.listenerSystem.addListener(eventId, listener);
    }

    public void setSize(int w, int h) {
        this.setWidth(w);
        this.setHeight(h);
    }

    public void setPreferredSize(int w, int h) {
        this.setPreferredWidth(w);
        this.setPreferredHeight(h);
    }

    public void setPreferredWidth(int preferredWidth) {
        this.preferredWidth = Math.max(0, preferredWidth);
    }

    public void setPreferredHeight(int preferredHeight) {
        this.preferredHeight = Math.max(0, preferredHeight);
    }

    public void setWidth(int width) {
        this.width = Math.max(0, width);
    }

    public void setHeight(int height) {
        this.height = Math.max(0, height);
    }

    public boolean isMouseAt(int mouseX, int mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public boolean isMouseAt(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getPreferredWidth() {
        return preferredWidth;
    }

    public int getPreferredHeight() {
        return preferredHeight;
    }

    public ElementStyle getStyle() {
        return style;
    }

    public final void setStyle(ElementStyle style) {
        this.style = style;
    }

}
