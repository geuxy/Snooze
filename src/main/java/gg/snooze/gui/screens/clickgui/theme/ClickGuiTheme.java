package gg.snooze.gui.screens.clickgui.theme;

import gg.snooze.gui.elements.UIElement;
import gg.snooze.gui.screens.clickgui.elements.CategoryElement;
import gg.snooze.gui.screens.clickgui.elements.ModuleElement;
import gg.snooze.gui.screens.clickgui.elements.properties.*;
import gg.snooze.gui.screens.clickgui.elements.properties.sub.OptionElement;
import net.minecraft.client.gui.DrawContext;

public interface ClickGuiTheme {

    void renderClickGui(DrawContext context, UIElement element, double mouseX, double mouseY);

    void renderCategoryArea(DrawContext context, UIElement area, double mouseX, double mouseY);
    void renderModuleArea(DrawContext context, UIElement area, double mouseX, double mouseY);
    void renderPropertyArea(DrawContext context, UIElement area, double mouseX, double mouseY);

    void renderCategory(DrawContext context, CategoryElement element, double mouseX, double mouseY);
    void renderModule(DrawContext context, ModuleElement element, double mouseX, double mouseY);

    void renderMode(DrawContext context, ModeElement<?> element, double mouseX, double mouseY);
    void renderMultiToggle(DrawContext context, MultiToggleElement element, double mouseX, double mouseY);
    void renderNote(DrawContext context, NoteElement element, double mouseX, double mouseY);
    void renderRange(DrawContext context, RangeElement element, double mouseX, double mouseY);
    void renderSlider(DrawContext context, SliderElement element, double mouseX, double mouseY);
    void renderToggle(DrawContext context, ToggleElement element, double mouseX, double mouseY);

    void renderOption(DrawContext context, OptionElement element, double mouseX, double mouseY);

}
