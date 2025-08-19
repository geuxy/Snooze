package gg.snooze.gui.screens.clickgui.elements;

import gg.snooze.gui.elements.UIElement;
import gg.snooze.property.Property;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public abstract class PropertyElement<T extends Property<?>> extends UIElement {

    private final T property;

    public abstract boolean mouseClicked(double mouseX, double mouseY, int button);
    public abstract void mouseReleased(double mouseX, double mouseY, int button);

}
