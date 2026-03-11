package gg.snooze.ui.clickgui.clickguis.dropdown;

import gg.snooze.ui.clickgui.theme.ClickGuiTheme;
import gg.snooze.ui.clickgui.theme.impl.DefaultTheme;
import gg.snooze.systems.module.info.ModuleType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;

public class DropdownScreen extends Screen {

    private final Dropdown[] dropdowns;

    private final ClickGuiTheme theme = new DefaultTheme();

    private int selectedIndex = 0;

    public DropdownScreen() {
        super(Component.empty());
        this.dropdowns = new Dropdown[ModuleType.values().length];
    }

    @Override
    protected void init() {
        ModuleType[] types = ModuleType.values();

        int dropWidth = 125;
        int dropHeight = 18;
        int dropGap = 25;

        for(int i = 0; i < types.length; i++) {
            this.dropdowns[i] = new Dropdown(theme, types[i],
                (i * (dropWidth + dropGap)) + dropGap, dropGap, // Position
                dropWidth, dropHeight, // Component Size
                dropHeight); // Gap Size
        }
    }

    @Override
    public void render(GuiGraphics context, int mouseX, int mouseY, float deltaTicks) {
        for(Dropdown dropdown : this.dropdowns) {
            dropdown.render(context, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean bl) {
        for(int i = this.dropdowns.length - 1; i > -1; i--) {
            if(this.dropdowns[i].mouseClicked(event.x(), event.y(), event.button())) {
                return true;
            }
        }
        return super.mouseClicked(event, bl);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        for(int i = this.dropdowns.length - 1; i > -1; i--) {
            this.dropdowns[i].mouseReleased(event.x(), event.y(), event.button());
        }
        return super.mouseReleased(event);
    }

}
