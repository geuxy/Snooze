package gg.snooze.ui.clickgui.theme;

import gg.snooze.ui.framework.UIElement;
import gg.snooze.ui.clickgui.elements.CategoryElement;
import gg.snooze.ui.clickgui.elements.ModuleElement;
import gg.snooze.ui.clickgui.elements.properties.*;
import gg.snooze.ui.clickgui.elements.properties.sub.OptionElement;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface ClickGuiTheme {

    void renderClickGui(GuiGraphicsExtractor context, UIElement element, double mouseX, double mouseY);

    void renderCategoryArea(GuiGraphicsExtractor context, UIElement area, double mouseX, double mouseY);
    void renderModuleArea(GuiGraphicsExtractor context, UIElement area, double mouseX, double mouseY);
    void renderPropertyArea(GuiGraphicsExtractor context, UIElement area, double mouseX, double mouseY);

    void renderCategory(GuiGraphicsExtractor context, CategoryElement element, double mouseX, double mouseY);
    void renderModule(GuiGraphicsExtractor context, ModuleElement element, double mouseX, double mouseY);

    void renderMode(GuiGraphicsExtractor context, ModeElement<?> element, double mouseX, double mouseY);
    void renderMultiToggle(GuiGraphicsExtractor context, MultiToggleElement element, double mouseX, double mouseY);
    void renderNote(GuiGraphicsExtractor context, NoteElement element, double mouseX, double mouseY);
    void renderRange(GuiGraphicsExtractor context, RangeElement element, double mouseX, double mouseY);
    void renderSlider(GuiGraphicsExtractor context, SliderElement element, double mouseX, double mouseY);
    void renderToggle(GuiGraphicsExtractor context, ToggleElement element, double mouseX, double mouseY);
    void renderOption(GuiGraphicsExtractor context, OptionElement element, double mouseX, double mouseY);

}
