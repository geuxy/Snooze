package gg.snooze.ui.framework.element.elements;

import gg.snooze.setting.settings.RangeSetting;
import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.event.events.MouseClickEvent;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

public class RangeElement extends BaseElement {

    private final Controller controller;

    private int minValueWidth, maxValueWidth;
    private int draggingDirection;

    public interface Controller {

        void setMinValue(double value);
        void setMaxValue(double value);
        double getMinValue();
        double getMaxValue();
        double getMin();
        double getMax();

        default String getText() {return "";}

    }

    public RangeElement(RangeSetting setting) {
        this(new Controller() {
            @Override public void setMinValue(double value) {setting.setMinValue(value);}
            @Override public void setMaxValue(double value) {setting.setMaxValue(value);}
            @Override public double getMinValue() {return setting.getMinValue();}
            @Override public double getMaxValue() {return setting.getMaxValue();}
            @Override public double getMin() {return setting.getMinimum();}
            @Override public double getMax() {return setting.getMaximum();}
            @Override public String getText() {return setting.name;}
        });
    }

    public RangeElement(Controller controller) {
        super();
        this.controller = controller;
        this.listen(MouseClickEvent.ID, e -> {
            MouseClickEvent event = (MouseClickEvent) e;

            if(event.isReleased()) {
                this.draggingDirection = 0;

            } else {
                if(event.getButton() == 0) {
                    if(isMouseAt(event.getMouseX(), event.getMouseY())) {
                        double valueWidthCenter = (minValueWidth + maxValueWidth) / 2D;
                        this.draggingDirection = event.getMouseX() > this.x + valueWidthCenter ? 1 : -1;
                    }
                }
            }
        });
    }

    @Override
    public void render(@NotNull ElementStyle style) {
        if(this.draggingDirection == 1) {
            this.maxValueWidth = Math.clamp(style.mx() - this.x, this.minValueWidth, this.width);

        } else if(draggingDirection == -1) {
            this.minValueWidth = Math.clamp(style.mx() - this.x, 0, this.maxValueWidth);
        }

        style.drawRange(this);
    }

    public void setMinValue(double value) {
        this.controller.setMinValue(Math.clamp(value, this.controller.getMin(), this.controller.getMaxValue()));

        double range = this.controller.getMax() - this.controller.getMin();
        double percentage = Math.clamp((this.controller.getMinValue() - this.controller.getMin()) / range, 0, 1);

        this.minValueWidth = (int) Math.round(percentage * this.width);
    }

    public void setMaxValue(double value) {
        this.controller.setMaxValue(Math.clamp(value, this.controller.getMinValue(), this.controller.getMax()));

        double range = this.controller.getMax() - this.controller.getMin();
        double percentage = Math.clamp((this.controller.getMaxValue() - this.controller.getMin()) / range, 0, 1);

        this.maxValueWidth = (int) Math.round(percentage * this.width);
    }

}
