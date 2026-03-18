package gg.snooze.ui.clickgui.elements.properties;

import gg.snooze.value.values.RangeValue;
import gg.snooze.ui.clickgui.elements.PropertyElement;
import gg.snooze.util.MouseUtil;
import lombok.Getter;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

// holy shitcode
public class RangeElement extends PropertyElement<RangeValue> {

    @Getter
    private double minValueWidth, maxValueWidth;

    private int draggingDirection;

    public RangeElement(RangeValue property) {
        super(property);
    }

    public void preRender(double mouseX, double mouseY) {
        if(draggingDirection == 1) {
            computeSliderValues(mouseX, (w, v) -> {
                if(w >= this.minValueWidth) {
                    this.maxValueWidth = w;
                    this.getProperty().setMaxValue(v);
                }
            });

        } else if(draggingDirection == -1) {
            computeSliderValues(mouseX, (w, v) -> {
                if(w <= this.maxValueWidth) {
                    this.minValueWidth = w;
                    this.getProperty().setMinValue(v);
                }
            });
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(MouseUtil.isMouseOver(mouseX, mouseY, this) && button == 0) {
            double valueWidthCenter = (minValueWidth + maxValueWidth) / 2;

            this.draggingDirection = mouseX > this.x + valueWidthCenter ? 1 : -1;
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        this.draggingDirection = 0;
    }

    @Override
    public void setSize(double width, double height) {
        super.setSize(width, height);
        this.updateValueWidth(this.getProperty().getMinValue(), v -> this.minValueWidth = v);
        this.updateValueWidth(this.getProperty().getMaxValue(), v -> this.maxValueWidth = v);
    }

    private void computeSliderValues(double mouseX, BiConsumer<Double, Double> consumer) {
        if(draggingDirection != 0) {
            RangeValue property = this.getProperty();

            double diff = Math.min(width, Math.max(0, mouseX - x));
            double range = property.getMaximum() - property.getMinimum();

            consumer.accept(
                Math.clamp(mouseX - this.x, 0, this.width),
                ((diff / this.width)) * range + property.getMinimum()
            );
        }
    }

    private void updateValueWidth(double value, Consumer<Double> consumer) {
        RangeValue property = this.getProperty();

        double min = property.getMinimum();
        double max = property.getMaximum();

        double percent = (value - min) / (max - min);

        percent = Math.clamp(percent, 0, 1);

        consumer.accept(percent * this.width);
    }

}
