package gg.snooze.ui.clickgui.style;

import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleType;
import gg.snooze.ui.core.Region;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;

public class ClickStyleImpl implements ClickStyle {

    private static final int OFF = 1;

    private static final int TYPE_BG = 0xFF1F1F24;
    private static final int MODULE_BG = 0xFF2F2F34;

    private GuiGraphicsExtractor graphics;
    private Font font;

    @Override
    public void setGraphics(GuiGraphicsExtractor graphics) {
        this.graphics = graphics;
        this.font = Minecraft.getInstance().font;
    }

    @Override
    public void drawType(ModuleType type, Region region) {
        graphics.fill(region.x, region.y, region.x + region.width, region.y + region.height, TYPE_BG);
        textCentered(type.name, region, -1);
    }

    @Override
    public void drawModuleRegion(Region region) {
        graphics.fill(region.x, region.y, region.x + region.width, region.y + region.height + OFF, TYPE_BG);
        graphics.fill(region.x + OFF, region.y, region.x + region.width - OFF, region.y + region.height, MODULE_BG);
    }

    @Override
    public void drawModule(Module module, Region region) {
        if(module.config.enabled) {
            graphics.fill(region.x + OFF, region.y, region.x + region.width - OFF, region.y + region.height, 0xFF2F42FF);
        }

        textCentered(module.metadata.name(), region, -1);
    }

    private void textCentered(String text, Region region, int color) {
        graphics.centeredText(
                font,
                text,
                region.x + (region.width / 2),
                region.y + (region.height / 2) - (font.lineHeight / 2),
                color
        );
    }

}
