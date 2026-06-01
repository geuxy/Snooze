package gg.snooze.ui.framework.element.elements.settings;

import gg.snooze.setting.settings.NumberSetting;
import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.event.events.MouseClickEvent;
import org.jetbrains.annotations.NotNull;

public class SliderElement extends BaseElement {

    @NotNull
    private final Controller controller;

    private boolean dragging;
    private int valueWidth;

    public interface Controller {

        void setValue(double value);
        double getValue();
        double getMin();
        double getMax();

        default String getText() {
            return "";
        }

    }

    public SliderElement(NumberSetting setting) {
        this(new Controller() {

            @Override
            public void setValue(double value) {
                setting.setValue(value);
            }

            @Override
            public double getValue() {
                return setting.getValue();
            }

            @Override
            public double getMin() {
                return setting.getMinimum();
            }

            @Override
            public double getMax() {
                return setting.getMaximum();
            }

            @Override
            public String getText() {
                return setting.name;
            }
        });
    }

    public SliderElement(@NotNull Controller controller) {
        super();
        this.controller = controller;
        this.listen(MouseClickEvent.ID, e -> {
            MouseClickEvent event = (MouseClickEvent) e;

            if(event.isReleased()) {
                this.dragging = false;

            } else {
                if(isMouseAt(event.getMouseX(), event.getMouseY())) {
                    if (event.getButton() == 0) {
                        this.dragging = true;
                    }
                }
            }
        });
    }

    @Override
    public void render(int mouseX, int mouseY) {
        if(this.dragging) {
            double diff = Math.clamp(mouseX - this.x, 0, this.width);
            double range = this.controller.getMax() - this.controller.getMin();

            this.valueWidth = (int) Math.round(diff);
            this.controller.setValue((diff / this.width) * range + this.controller.getMin());
        }

        if(this.getStyle() != null) {
            this.getStyle().drawSlider(this, mouseX, mouseY);
        }
    }

    public int getValueWidth() {
        return valueWidth;
    }

    public void setValue(double value) {
        this.controller.setValue(Math.clamp(value, this.controller.getMin(), this.controller.getMax()));

        double range = this.controller.getMax() - this.controller.getMin();
        double percentage = Math.clamp((this.controller.getValue() - this.controller.getMin()) / range, 0, 1);

        this.valueWidth = (int) Math.round(percentage * this.width);
    }

}
