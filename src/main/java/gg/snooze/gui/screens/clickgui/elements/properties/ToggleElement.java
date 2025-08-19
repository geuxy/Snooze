package gg.snooze.gui.screens.clickgui.elements.properties;

import gg.snooze.gui.screens.clickgui.elements.PropertyElement;
import gg.snooze.property.impl.ToggleProperty;
import gg.snooze.util.MouseUtil;

public class ToggleElement extends PropertyElement<ToggleProperty> {

    public ToggleElement(ToggleProperty property) {
        super(property);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(MouseUtil.isMouseOver(mouseX, mouseY, this) && button == 0) {
            this.getProperty().toggle();
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
    }

}
