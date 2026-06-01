package gg.snooze.ui.framework.element.elements.containers;

import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.event.events.MouseScrollEvent;
import gg.snooze.ui.framework.event.events.ResizeSceneEvent;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

public class ScrollPaneElement extends BaseContainerElement<ScrollPaneElement> {

    public int step, value, minimum, maximum;

    public ScrollPaneElement() {
        this.step = 20;
        this.value = 0;
        this.minimum = Integer.MIN_VALUE;
        this.maximum = Integer.MAX_VALUE;
        this.listen(ResizeSceneEvent.ID, _ -> this.updateLayout());
        this.listen(MouseScrollEvent.ID, e -> {
            MouseScrollEvent event = (MouseScrollEvent) e;

            int direction = event.getDirection();

            if(direction == 0) {
                return;
            }

            this.value += direction > 0 ? -this.step : this.step;
            this.value = Math.clamp(this.value, minimum, maximum);
            this.updateLayout();
        });
    }

    @Override
    public void render(@NotNull ElementStyle style) {
        style.drawPanel(this);

        this.getChildren().forEach(e -> e.render(style));
    }

}
