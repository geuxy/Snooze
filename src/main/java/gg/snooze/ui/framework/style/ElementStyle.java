package gg.snooze.ui.framework.style;

import gg.snooze.ui.framework.element.elements.*;
import gg.snooze.ui.framework.element.elements.containers.SceneElement;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface ElementStyle {

    int mx();
    int my();

    void update(GuiGraphicsExtractor graphics, int mouseX, int mouseY);
    void drawButton(ButtonElement e);
    void drawPanel(BaseContainerElement<?> e);
    void drawLabel(LabelElement e);
    void drawToggle(ToggleElement e);
    void drawScene(SceneElement e);
    void drawSlider(SliderElement e);
    void drawRange(RangeElement e);

}

