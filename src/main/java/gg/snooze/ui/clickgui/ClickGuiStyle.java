package gg.snooze.ui.clickgui;

import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.element.elements.ButtonElement;
import gg.snooze.ui.framework.element.elements.LabelElement;
import gg.snooze.ui.framework.element.elements.containers.SceneElement;
import gg.snooze.ui.framework.element.elements.RangeElement;
import gg.snooze.ui.framework.element.elements.SliderElement;
import gg.snooze.ui.framework.element.elements.ToggleElement;
import gg.snooze.ui.framework.style.ElementStyle;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class ClickGuiStyle implements ElementStyle {

    private static final int BUTTON_COLOR = -1;
    private static final int PANEL_COLOR = 0xFF505050;

    private GuiGraphicsExtractor graphics;
    private int mouseX, mouseY;

    @Override
    public int mx() {
        return this.mouseX;
    }

    @Override
    public int my() {
        return this.mouseY;
    }

    @Override
    public void update(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        this.graphics = graphics;
        this.mouseX = mouseX;
        this.mouseY = mouseY;
    }

    @Override
    public void drawButton(ButtonElement e) {
        graphics.fill(e.x, e.y, e.x + e.width, e.y + e.height, BUTTON_COLOR);
    }

    @Override
    public void drawPanel(BaseContainerElement<?> e) {
        graphics.fill(e.x, e.y, e.x + e.width, e.y + e.height, PANEL_COLOR);
    }

    @Override
    public void drawLabel(LabelElement e) {

    }

    @Override
    public void drawToggle(ToggleElement e) {

    }

    @Override
    public void drawScene(SceneElement e) {

    }

    @Override
    public void drawSlider(SliderElement e) {

    }

    @Override
    public void drawRange(RangeElement e) {

    }
}
