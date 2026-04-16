package gg.snooze.ui.clickgui.elements.properties;

import gg.snooze.value.values.BoolValue;
import gg.snooze.ui.clickgui.elements.PropertyElement;
import gg.snooze.util.MouseUtil;

public class BoolElement extends PropertyElement<BoolValue> {

    public BoolElement(BoolValue property) {
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
