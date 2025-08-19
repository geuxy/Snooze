package gg.snooze.gui.screens.clickgui.clickguis.dropdown;

import gg.snooze.gui.screens.clickgui.theme.ClickGuiTheme;
import gg.snooze.gui.screens.clickgui.theme.impl.DefaultTheme;
import gg.snooze.module.info.ModuleType;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.ParentElement;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.screen.option.OptionsScreen;
import net.minecraft.text.Text;

public class DropdownScreen extends Screen {

    private final Dropdown[] dropdowns;

    private final ClickGuiTheme theme = new DefaultTheme();

    private int selectedIndex = 0;

    public DropdownScreen() {
        super(Text.of(""));
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
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        for(Dropdown dropdown : this.dropdowns) {
            dropdown.render(context, mouseX, mouseY);
        }
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        for(int i = this.dropdowns.length - 1; i > -1; i--) {
            if(this.dropdowns[i].mouseClicked(mouseX, mouseY, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        for(int i = this.dropdowns.length - 1; i > -1; i--) {
            this.dropdowns[i].mouseReleased(mouseX, mouseY, button);
        }
        return false;
    }

}
