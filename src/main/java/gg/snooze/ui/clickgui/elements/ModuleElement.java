package gg.snooze.ui.clickgui.elements;

import gg.snooze.value.BaseValue;
import gg.snooze.ui.framework.UIElement;
import gg.snooze.ui.clickgui.elements.properties.*;
import gg.snooze.ui.clickgui.elements.properties.sub.OptionElement;
import gg.snooze.ui.clickgui.theme.ClickGuiTheme;
import gg.snooze.module.Module;
import gg.snooze.value.values.*;
import gg.snooze.util.MouseUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.minecraft.client.gui.GuiGraphicsExtractor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @RequiredArgsConstructor
public class ModuleElement extends UIElement {

    private final List<PropertyElement<? extends BaseValue<?>>> properties = new ArrayList<>();

    private final Module module;

    private UIElement propertyArea;

    private double originalHeight, propertyHeight;

    public void createProperties() {
        this.clearProperties();
        this.module.getProperties().values().stream().map(property ->
            switch(property) {
                case ModeValue<?> p -> new ModeElement<>(p);
                case MultiValue p -> new MultiToggleElement(p);
                case LabelValue p -> new NoteElement(p);
                case RangeValue p -> new RangeElement(p);
                case NumberValue p -> new SliderElement(p);
                case BoolValue p -> new ToggleElement(p);
                default -> null;
            }

        ).filter(Objects::nonNull).forEach(this.properties::add);

        this.propertyArea = new UIElement();
    }

    public void clearProperties() {
        this.properties.clear();
        this.propertyHeight = 0;
        this.propertyArea = null;
    }

    public void updateProperties() {
        if(propertyArea == null) return;

        double propertyY = originalHeight;

        for(PropertyElement<? extends BaseValue<?>> property : this.properties) {
            property.setPosition(x, y + propertyY);
            property.setSize(width, originalHeight);

            propertyY += property.getHeight();
        }

        this.propertyHeight = propertyY - originalHeight;

        this.propertyArea.setPosition(x, y + originalHeight);
        this.propertyArea.setSize(width, propertyHeight);
    }

    public void renderProperties(GuiGraphicsExtractor context, ClickGuiTheme theme, double mouseX, double mouseY) {
        if(propertyArea == null) return;

        theme.renderPropertyArea(context, propertyArea, mouseX, mouseY);

        for(PropertyElement<? extends BaseValue<?>> property : this.properties) {
            if(property instanceof ModeElement<?> e) {
                theme.renderMode(context, e, mouseX, mouseY);

                if(e.getOptions() != null && !e.getOptions().isEmpty()) {
                    for(OptionElement option : e.getOptions()) {
                        theme.renderOption(context, option, mouseX, mouseY);
                    }
                }

            } else if(property instanceof MultiToggleElement e) {
                theme.renderMultiToggle(context, e, mouseX, mouseY);

                if(e.getOptions() != null && !e.getOptions().isEmpty()) {
                    for(OptionElement option : e.getOptions()) {
                        theme.renderOption(context, option, mouseX, mouseY);
                    }
                }

            } else if(property instanceof NoteElement e) {
                theme.renderNote(context, e, mouseX, mouseY);

            } else if(property instanceof RangeElement e) {
                e.preRender(mouseX, mouseY);
                theme.renderRange(context, e, mouseX, mouseY);

            } else if(property instanceof SliderElement e) {
                e.preRender(mouseX, mouseY);
                theme.renderSlider(context, e, mouseX, mouseY);

            } else if(property instanceof ToggleElement e) {
                theme.renderToggle(context, e, mouseX, mouseY);
            }
        }
    }

    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if(MouseUtil.isMouseOver(mouseX, mouseY, x, y, width, originalHeight)) {
            if(button == 0) {
                this.module.setEnabled(!this.module.getConfig().isEnabled());

            } else if(button == 1) {
                if (propertyArea == null) {
                    this.createProperties();

                } else {
                    this.clearProperties();
                }
            }
        } else {
            if(propertyArea != null) {
                for(PropertyElement<?> property : this.properties) {
                    if(property.mouseClicked(mouseX, mouseY, button)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    public void mouseReleased(double mouseX, double mouseY, int button) {
        if(propertyArea != null) {
            for(PropertyElement<?> property : this.properties) {
                property.mouseReleased(mouseX, mouseY, button);
            }
        }
    }

    @Override
    public void setSize(double width, double height) {
        this.originalHeight = height;
        super.setSize(width, height + propertyHeight);
    }

}
