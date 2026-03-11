package gg.snooze.ui.clickgui.theme;

import gg.snooze.ui.framework.UIElement;
import gg.snooze.ui.clickgui.elements.CategoryElement;
import gg.snooze.ui.clickgui.elements.ModuleElement;
import gg.snooze.ui.clickgui.elements.properties.*;
import gg.snooze.ui.clickgui.elements.properties.sub.OptionElement;
import net.minecraft.client.gui.GuiGraphics;

public interface ClickGuiTheme {

    void renderClickGui(GuiGraphics context, UIElement element, double mouseX, double mouseY);

    void renderCategoryArea(GuiGraphics context, UIElement area, double mouseX, double mouseY);
    void renderModuleArea(GuiGraphics context, UIElement area, double mouseX, double mouseY);
    void renderPropertyArea(GuiGraphics context, UIElement area, double mouseX, double mouseY);

    void renderCategory(GuiGraphics context, CategoryElement element, double mouseX, double mouseY);
    void renderModule(GuiGraphics context, ModuleElement element, double mouseX, double mouseY);

    void renderMode(GuiGraphics context, ModeElement<?> element, double mouseX, double mouseY);
    void renderMultiToggle(GuiGraphics context, MultiToggleElement element, double mouseX, double mouseY);
    void renderNote(GuiGraphics context, NoteElement element, double mouseX, double mouseY);
    void renderRange(GuiGraphics context, RangeElement element, double mouseX, double mouseY);
    void renderSlider(GuiGraphics context, SliderElement element, double mouseX, double mouseY);
    void renderToggle(GuiGraphics context, ToggleElement element, double mouseX, double mouseY);
    void renderOption(GuiGraphics context, OptionElement element, double mouseX, double mouseY);

}
