package gg.snooze.ui.clickgui.elements.properties;

import gg.snooze.ui.clickgui.elements.PropertyElement;
import gg.snooze.value.values.LabelValue;

public class LabelElement extends PropertyElement<LabelValue> {

    public LabelElement(LabelValue property) {
        super(property);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        return false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
    }

}
