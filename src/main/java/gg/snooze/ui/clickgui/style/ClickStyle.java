package gg.snooze.ui.clickgui.style;

import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleType;
import gg.snooze.ui.core.Region;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public interface ClickStyle {

    void setGraphics(GuiGraphicsExtractor graphics);

    void drawType(ModuleType type, Region region);
    void drawModuleRegion(Region region);
    void drawModule(Module module, Region region);

}
