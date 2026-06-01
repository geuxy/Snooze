package gg.snooze.ui.framework.element.elements.containers;

import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.event.events.ResizeSceneEvent;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

public class SceneElement extends BaseContainerElement<SceneElement> {

    @Override
    public void render(@NotNull ElementStyle style) {
        style.drawScene(this);
        this.getChildren().forEach(e -> e.render(style));
    }

    @Override
    public boolean isResizable() {
        return false;
    }

    public void resizeScene(double width, double height) {
        if (width <= 0 || height <= 0) {
            return;
        }

        int w = (int) width;
        int h = (int) height;

        this.x = 0;
        this.y = 0;
        this.width = w;
        this.height = h;
        this.preferredWidth = w;
        this.preferredHeight = h;

        this.updateLayout();

        this.invoke(ResizeSceneEvent.ID, new ResizeSceneEvent((int) width, (int) height));
    }

}
