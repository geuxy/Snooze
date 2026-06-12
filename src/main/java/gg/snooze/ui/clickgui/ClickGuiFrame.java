package gg.snooze.ui.clickgui;

import gg.snooze.Snooze;
import gg.snooze.module.info.ModuleType;
import gg.snooze.ui.clickgui.style.ClickStyle;
import gg.snooze.ui.core.MouseUtil;
import gg.snooze.ui.core.Region;
import net.minecraft.client.input.MouseButtonEvent;

import java.util.List;

public class ClickGuiFrame extends Region {

    private final ModuleType type;
    private final Region moduleRegion;
    private final int moduleHeight;
    private final List<ClickModule> modules;

    public ClickGuiFrame(ModuleType type, int moduleHeight) {
        this.type = type;
        this.moduleRegion = new Region();
        this.moduleHeight = moduleHeight;
        this.modules = Snooze.INSTANCE.modules.getModules(this.type)
                .stream()
                .map(ClickModule::new)
                .toList();
    }

    @Override
    public void setPosition(int x, int y) {
        super.setPosition(x, y);
        this.layoutModules();
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.layoutModules();
    }

    private void layoutModules() {
        int moduleY = y + height;

        for(var module : this.modules) {
            module.setPosition(x, moduleY);
            module.setSize(width, moduleHeight);
            moduleY += module.height;
        }

        this.moduleRegion.setPosition(x, y + height);
        this.moduleRegion.setSize(width, moduleY - (y + height));
    }

    @Override
    public boolean canDrag(int mouseX, int mouseY) {
        return MouseUtil.isAt(mouseX, mouseY, this.x, this.y, this.width, this.height);
    }

    public void render(ClickStyle style) {
        style.drawType(this.type, this);
        style.drawModuleRegion(this.moduleRegion);
        this.modules.forEach(m -> m.render(style));
    }

    public boolean click(MouseButtonEvent event) {
        if(event.button() != 0 && event.button() != 1) {
            return false;
        }

        return this.modules.reversed().stream().anyMatch(m -> m.click(event));
    }

    public boolean release(MouseButtonEvent event) {
        return this.modules.reversed().stream().anyMatch(m -> m.release(event));
    }


}
