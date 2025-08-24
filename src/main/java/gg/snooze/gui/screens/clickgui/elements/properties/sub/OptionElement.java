package gg.snooze.gui.screens.clickgui.elements.properties.sub;

import gg.snooze.gui.framework.UIElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class OptionElement extends UIElement {

    private final String name;
    private boolean value;

}
