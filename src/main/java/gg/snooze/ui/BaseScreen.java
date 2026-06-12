package gg.snooze.ui;

import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class BaseScreen extends Screen {

    public int mouseX, mouseY;

    public BaseScreen() {
        super(Component.empty());
    }

    public void render(GuiGraphicsExtractor graphics) {
    }

    public boolean click(MouseButtonEvent event) {
        return false;
    }

    public boolean release(MouseButtonEvent event) {
        return false;
    }

    public boolean scroll(int direction) {
        return false;
    }

    @Override
    public final void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        this.render(graphics);
    }

    @Override
    public final boolean mouseClicked(@NonNull MouseButtonEvent event, boolean doubleClick) {
        return this.click(event) || super.mouseClicked(event, doubleClick);
    }

    @Override
    public final boolean mouseReleased(MouseButtonEvent event) {
        return release(event) || super.mouseReleased(event);
    }

    @Override
    public final boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        return scroll((int) Math.signum(scrollY)) || super.mouseScrolled(x, y, scrollX, scrollY);
    }

}
