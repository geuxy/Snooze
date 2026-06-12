package gg.snooze.ui.core;

public class Region {

    public int x, y, width, height;

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setSize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public boolean canDrag(int mouseX, int mouseY) {
        return false;
    }

}
