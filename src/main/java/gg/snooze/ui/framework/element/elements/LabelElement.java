package gg.snooze.ui.framework.element.elements;

import gg.snooze.ui.framework.element.BaseElement;

public class LabelElement extends BaseElement {

    public String text;

    @Override
    public void render(int mouseX, int mouseY) {
        if(this.getStyle() != null) {
            this.getStyle().drawLabel(this, mouseX, mouseY);
        }
    }

}
