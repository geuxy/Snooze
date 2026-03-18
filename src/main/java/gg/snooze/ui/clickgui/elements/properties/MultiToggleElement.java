package gg.snooze.ui.clickgui.elements.properties;

import gg.snooze.ui.clickgui.elements.PropertyElement;
import gg.snooze.ui.clickgui.elements.properties.sub.OptionElement;
import gg.snooze.value.values.MultiValue;
import gg.snooze.util.MouseUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
public class MultiToggleElement extends PropertyElement<MultiValue> {

    private final List<OptionElement> options = new ArrayList<>();

    private double originalHeight;
    private boolean open;

    public MultiToggleElement(MultiValue property) {
        super(property);
    }

    private void toggle() {
        this.open = !this.open;

        if(open) {
            for(Map.Entry<String, Boolean> option : this.getProperty().getEntries().entrySet()) {
                this.options.add(new OptionElement(option.getKey(), option.getValue()));
            }

        } else {
            this.options.clear();
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(button != 0 && button != 1) return false;

        // Hovered over setting
        if(MouseUtil.isMouseOver(mouseX, mouseY, x, y, width, originalHeight)) {
            this.toggle();
            return true;

        } else if(open && MouseUtil.isMouseOver(mouseX, mouseY, x, y + originalHeight, width, height - originalHeight) && button == 0) {
            for(OptionElement option : this.options) {
                if(MouseUtil.isMouseOver(mouseX, mouseY, option)) {
                    boolean newValue = !option.isValue();

                    option.setValue(newValue);
                    this.getProperty().getEntries().put(option.getName(), newValue);
                    return true;
                }
            }
        }

        return false;
    }

    @Override
    public void mouseReleased(double mouseX, double mouseY, int button) {
    }

    @Override
    public void setPosition(double x, double y) {
        double entryY = y + originalHeight;

        for(OptionElement option : this.options) {
            option.setPosition(x, entryY);
            entryY += originalHeight;

        }
        super.setPosition(x, y);
    }

    @Override
    public void setSize(double width, double height) {
        this.originalHeight = height;

        for(OptionElement option : this.options) {
            option.setSize(width, originalHeight);
        }

        double extraHeight = 0;

        if(this.open) {
            extraHeight += (long) this.getProperty().getEntries().size() * originalHeight;
        }

        super.setSize(width, this.originalHeight + extraHeight);
    }

}

