package gg.snooze.ui.clickgui;

import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.element.elements.ButtonElement;
import gg.snooze.ui.framework.element.elements.LabelElement;
import gg.snooze.ui.framework.element.elements.containers.SceneElement;
import gg.snooze.ui.framework.element.elements.settings.RangeElement;
import gg.snooze.ui.framework.element.elements.settings.SliderElement;
import gg.snooze.ui.framework.element.elements.settings.ToggleElement;
import gg.snooze.ui.framework.style.ElementStyle;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class ClickGuiStyle implements ElementStyle {

    private static final int BUTTON_COLOR = -1;
    private static final int PANEL_COLOR = 0xFF505050;

    private GuiGraphicsExtractor graphics;

    @Override
    public void setGraphics(GuiGraphicsExtractor graphics) {
        this.graphics = graphics;
    }

    @Override
    public void drawButton(ButtonElement button, int mouseX, int mouseY) {
        graphics.fill(button.getX(), button.getY(), button.getX() + button.getWidth(), button.getY() + button.getHeight(), BUTTON_COLOR);
    }

    @Override
    public void drawPanel(BaseContainerElement<?> panel, int mouseX, int mouseY) {
        graphics.fill(5, 5, 100, 100, PANEL_COLOR);
        System.out.println("huh");
    }

    @Override
    public void drawLabel(LabelElement label, int mouseX, int mouseY) {
    }

    @Override
    public void drawToggle(ToggleElement toggle, int mouseX, int mouseY) {
    }

    @Override
    public void drawScene(SceneElement scene, int mouseX, int mouseY) {
        graphics.fill(5, 5, 100, 100, PANEL_COLOR);
    }

    @Override
    public void drawSlider(SliderElement slider, int mouseX, int mouseY) {
    }

    @Override
    public void drawRange(RangeElement slider, int mouseX, int mouseY) {
    }

}
