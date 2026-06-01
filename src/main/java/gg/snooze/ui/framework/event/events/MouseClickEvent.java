package gg.snooze.ui.framework.event.events;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.event.BaseEvent;

public class MouseClickEvent extends BaseEvent {

    public static final int ID = 0;

    private final int button;
    private final boolean released;
    public final int mouseX, mouseY;

    public MouseClickEvent(int button, boolean released, int mouseX, int mouseY) {
        this.button = button;
        this.released = released;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public boolean canInvoke(BaseElement element) {
        return element.isMouseAt(mouseX, mouseY);
    }

    public int getButton() {
        return button;
    }

    public boolean isReleased() {
        return released;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

}
