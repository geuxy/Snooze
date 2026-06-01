package gg.snooze.ui.framework.element.elements.containers;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.event.Listener;
import gg.snooze.ui.framework.event.events.MouseScrollEvent;
import gg.snooze.ui.framework.event.events.ResizeSceneEvent;

public class ScrollPaneElement extends BaseContainerElement<ScrollPaneElement> {

    private int step = 20;
    private int value = 0;
    private int minValue = Integer.MIN_VALUE;
    private int maxValue = Integer.MAX_VALUE;

    public ScrollPaneElement() {
        this.listen(ResizeSceneEvent.ID, _ -> this.updateLayout());
        this.listen(MouseScrollEvent.ID, onMouseScroll);
    }

    private final Listener<MouseScrollEvent> onMouseScroll = event -> {
        int direction = event.getDirection();

        if(direction == 0) {
            return;
        }

        this.value += direction > 0 ? -this.step : this.step;
        this.updateLayout();
    };

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

    public int getValue() {
        return value;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

}
