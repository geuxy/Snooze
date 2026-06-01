package gg.snooze.ui.framework.element.elements.containers;

import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.event.events.ResizeSceneEvent;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

public class PanelElement extends BaseContainerElement {

    public PanelElement() {
        this.listen(ResizeSceneEvent.ID, _ -> this.updateLayout());
    }

    @Override
    public void render(@NotNull ElementStyle style) {
        style.drawPanel(this);
        this.getChildren().forEach(e -> e.render(style));
    }

}
