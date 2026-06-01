package gg.snooze.ui.framework.event.events;

import gg.snooze.ui.framework.event.BaseEvent;

public class MouseScrollEvent extends BaseEvent {

    public static final int ID = 1;

    private final int direction;
    private final double mouseX, mouseY;

    public MouseScrollEvent(int direction, double mouseX, double mouseY) {
        this.direction = direction;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    public int getDirection() {
        return direction;
    }

    public double getMouseX() {
        return mouseX;
    }

    public double getMouseY() {
        return mouseY;
    }

}
