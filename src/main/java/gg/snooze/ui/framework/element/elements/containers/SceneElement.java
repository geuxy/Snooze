package gg.snooze.ui.framework.element.elements.containers;

import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.event.events.ResizeSceneEvent;

public class SceneElement extends BaseContainerElement<SceneElement> {

    @Override
    public void render(int mouseX, int mouseY) {
        if(this.getStyle() != null) {
            this.getStyle().drawScene(this, mouseX, mouseY);
        }

        this.getChildren().forEach(e -> e.render(mouseX, mouseY));
    }

    public void resizeScene(double width, double height) {
        if (width <= 0 || height <= 0) {
            return;
        }

        this.setX(0);
        this.setY(0);
        this.setWidth((int) width);
        this.setHeight((int) height);
        this.setPreferredWidth((int) width);
        this.setPreferredHeight((int) height);

        this.updateLayout();

        this.invoke(ResizeSceneEvent.ID, new ResizeSceneEvent((int) width, (int) height));
    }

    @Override
    public boolean isResizable() {
        return false;
    }

}
