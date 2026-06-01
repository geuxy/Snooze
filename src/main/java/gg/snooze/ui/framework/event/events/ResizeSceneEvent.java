package gg.snooze.ui.framework.event.events;

import gg.snooze.ui.framework.event.BaseEvent;

public class ResizeSceneEvent extends BaseEvent {

    public static final int ID = 2;

    private final int width, height;

    public ResizeSceneEvent(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

}
