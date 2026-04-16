package gg.snooze.ui.clickgui.elements.properties.sub;

import gg.snooze.ui.framework.UIElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor @Getter @Setter
public class OptionElement extends UIElement {

    private final String name;
    private boolean value;

}
