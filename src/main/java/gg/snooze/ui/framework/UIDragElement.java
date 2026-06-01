package gg.snooze.ui.framework;

import gg.snooze.ui.framework.element.BaseElement;

public abstract class UIDragElement extends BaseElement {

    private int lastX, lastY;

    public void animateDrag(double mouseX, double mouseY) {
        this.x = (int) Math.round(mouseX) - lastX;
        this.y = (int) Math.round(mouseY) - lastY;
    }

    public void startDrag(double mouseX, double mouseY) {
        this.lastX = (int) Math.round(mouseX) - x;
        this.lastY = (int) Math.round(mouseY) - y;
    }

    public void stopDrag() {
        this.lastX = 0;
        this.lastY = 0;
    }

}
