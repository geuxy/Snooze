package gg.snooze.ui.framework.style;

import gg.snooze.ui.framework.element.elements.*;
import gg.snooze.ui.framework.element.elements.containers.SceneElement;
import gg.snooze.ui.framework.element.elements.settings.*;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface ElementStyle {

    void setGraphics(GuiGraphicsExtractor graphics);
    void drawButton(ButtonElement button, int mouseX, int mouseY);
    void drawPanel(BaseContainerElement<?> panel, int mouseX, int mouseY);
    void drawLabel(LabelElement label, int mouseX, int mouseY);
    void drawToggle(ToggleElement toggle, int mouseX, int mouseY);
    void drawScene(SceneElement scene, int mouseX, int mouseY);
    void drawSlider(SliderElement slider, int mouseX, int mouseY);
    void drawRange(RangeElement slider, int mouseX, int mouseY);

}

