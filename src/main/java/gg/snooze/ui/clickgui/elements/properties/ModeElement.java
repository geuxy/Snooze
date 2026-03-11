package gg.snooze.ui.clickgui.elements.properties;

import gg.snooze.ui.clickgui.elements.PropertyElement;
import gg.snooze.ui.clickgui.elements.properties.sub.OptionElement;
import gg.snooze.systems.property.properties.ModeProperty;
import gg.snooze.util.MouseUtil;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ModeElement<E> extends PropertyElement<ModeProperty<E>> {

    private final List<OptionElement> options = new ArrayList<>();

    private double originalHeight;

    private boolean open;

    public ModeElement(ModeProperty<E> property) {
        super(property);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(button != 1 && button != 0) return false;

        if(MouseUtil.isMouseOver(mouseX, mouseY, x, y, width, originalHeight)) {
            this.toggle();
            return true;

        } else if(open && MouseUtil.isMouseOver(mouseX, mouseY, this) && button == 0) {
            ModeProperty<E> property = this.getProperty();

            for(int i = 0; i < property.getOptions().length; i++) {
                double optionY = y + ((i + 1) * originalHeight);

                if(MouseUtil.isMouseOver(mouseX, mouseY, x, optionY, width, originalHeight)) {
                    this.options.get(property.getValueIndex()).setValue(false);
                    property.setValue(i);
                    this.options.get(i).setValue(property.getValueIndex() == i);
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
            extraHeight += (long) this.getProperty().getOptions().length * originalHeight;
        }

        super.setSize(width, this.originalHeight + extraHeight);
    }

    private void toggle() {
        this.open = !this.open;

        if(open) {
            for(E option : this.getProperty().getOptions()) {
                String optionName = this.getProperty().getModeName(option);
                boolean selected = this.getProperty().getValue() == option;

                this.options.add(new OptionElement(optionName, selected));
            }
        } else {
            this.options.clear();
        }
    }

}
