package gg.snooze.ui.clickgui.elements.properties;

import gg.snooze.ui.clickgui.elements.PropertyElement;
import gg.snooze.systems.property.properties.NoteProperty;

public class NoteElement extends PropertyElement<NoteProperty> {

    public NoteElement(NoteProperty property) {
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
