package gg.snooze.ui.clickgui.elements.properties;

import gg.snooze.value.values.DoubleBaseValue;
import gg.snooze.ui.clickgui.elements.PropertyElement;
import gg.snooze.util.MouseUtil;
import lombok.Getter;

@Getter
public class SliderElement extends PropertyElement<DoubleBaseValue> {

    private double valueWidth;
    private boolean dragging;
    private String valueText;

    public SliderElement(DoubleBaseValue property) {
        super(property);
        this.updateValueText();
    }

    public void preRender(double mouseX, double mouseY) {
        if(dragging) {
            DoubleBaseValue property = this.getProperty();
            double min = property.getMinimum();
            double max = property.getMaximum();
            double diff = Math.min(width, Math.max(0, mouseX - x));
            double range = max - min;

            this.valueWidth = Math.clamp(mouseX - this.x, 0, this.width);

            property.setValue(((diff / this.width)) * range + min);
            this.updateValueText();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(MouseUtil.isMouseOver(mouseX, mouseY, this) && button == 0) {
            this.dragging = true;
            return true;
        }
        return false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
        this.dragging = false;
    }

    @Override
    public void setSize(double width, double height) {
        super.setSize(width, height);
        this.updateValueWidth();
    }

    private void updateValueWidth() {
        DoubleBaseValue property = this.getProperty();

        double min = property.getMinimum();
        double max = property.getMaximum();
        double value = property.getValue();
        double percent = (value - min) / (max - min);

        percent = Math.clamp(percent, 0, 1);

        this.valueWidth = percent * this.width;
    }

    private void updateValueText() {
        this.valueText = String.format("%.2f", this.getProperty().getValue());
    }

}
