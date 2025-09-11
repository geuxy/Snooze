package gg.snooze.ui.framework;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UIElement {

    protected double x, y, width, height;

    public void setPosition(double x, double y) {
        this.setX(x);
        this.setY(y);
    }

    public void setSize(double width, double height) {
        this.setWidth(width);
        this.setHeight(height);
    }

}
