package gg.snooze.gui.screens.clickgui.elements.properties;

import gg.snooze.gui.screens.clickgui.elements.PropertyElement;
import gg.snooze.property.impl.NoteProperty;

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
