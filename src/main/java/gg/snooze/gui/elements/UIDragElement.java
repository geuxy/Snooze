package gg.snooze.gui.elements;

import lombok.Getter;
import lombok.Setter;

@Getter
public class UIDragElement extends UIElement {

    private double lastX, lastY;
    @Setter private boolean dragging;

    public void onDrag(double mouseX, double mouseY) {}

    public void animateDrag(double mouseX, double mouseY) {
        if(this.dragging) {
            this.setX(mouseX - lastX);
            this.setY(mouseY - lastY);
            this.onDrag(mouseX, mouseY);
        }
    }

    public void startDrag(double mouseX, double mouseY) {
        this.lastX = mouseX - x;
        this.lastY = mouseY - y;
        this.dragging = true;
    }

    public void stopDrag() {
        this.lastX = 0;
        this.lastY = 0;
        this.dragging = false;
    }

}
