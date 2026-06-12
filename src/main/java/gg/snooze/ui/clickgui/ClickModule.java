package gg.snooze.ui.clickgui;

import gg.snooze.module.Module;
import gg.snooze.ui.clickgui.style.ClickStyle;
import gg.snooze.ui.core.MouseUtil;
import gg.snooze.ui.core.Region;
import net.minecraft.client.input.MouseButtonEvent;

public class ClickModule extends Region {

    public final Module module;
    private int originalHeight;

    public ClickModule(Module module) {
        this.module = module;
    }

    public void render(ClickStyle style) {
        style.drawModule(this.module, this);
    }

    public boolean click(MouseButtonEvent event) {
        if(!MouseUtil.isAt(this, event.x(), event.y())) {
            return false;
        }

        if(MouseUtil.isAt(event.x(), event.y(), x, y, width, originalHeight)) {
            this.module.toggle();

        } else {
            // Configuring settings
        }

        return true;
    }

    public boolean release(MouseButtonEvent event) {
        return false;
    }

    @Override
    public void setSize(int width, int height) {
        super.setSize(width, height);
        this.originalHeight = height;
    }

}
