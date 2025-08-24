package gg.snooze.util;

import gg.snooze.gui.framework.UIElement;
import lombok.experimental.UtilityClass;

@UtilityClass
public class MouseUtil {

    public boolean isMouseOver(double mouseX, double mouseY, UIElement element) {
        return isMouseOver(mouseX, mouseY, element.getX(), element.getY(), element.getWidth(), element.getHeight());
    }

    public boolean isMouseOver(double mouseX, double mouseY, double x, double y, double w, double h) {
        return mouseX >= x && mouseX <= x + w && mouseY >= y && mouseY <= y + h;
    }

}
