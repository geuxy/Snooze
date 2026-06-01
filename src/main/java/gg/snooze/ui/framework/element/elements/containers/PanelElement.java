package gg.snooze.ui.framework.element.elements.containers;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.event.events.ResizeSceneEvent;

public class PanelElement extends BaseContainerElement<PanelElement> {

    public PanelElement() {
        this.listen(ResizeSceneEvent.ID, _ -> this.updateLayout());
    }

    @Override
    public void render(int mouseX, int mouseY) {
        if(this.getStyle() != null) {
            this.getStyle().drawPanel(this, mouseX, mouseY);
        }

        if(this.getChildren().isEmpty()) {
            return;
        }

        for(BaseElement child : this.getChildren()) {
            child.render(mouseX, mouseY);
        }
    }

}
