package gg.snooze.ui.clickgui.elements;

import gg.snooze.value.BaseValue;
import gg.snooze.ui.framework.UIElement;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public abstract class PropertyElement<T extends BaseValue<?>> extends UIElement {

    private final T property;

    public abstract boolean mouseClicked(double mouseX, double mouseY, int button);
    public abstract void mouseReleased(double mouseX, double mouseY, int button);

}
