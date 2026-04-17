package gg.snooze.module.modules;

import gg.snooze.ui.clickgui.clickguis.dropdown.DropdownScreen;
import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;
import gg.snooze.util.exceptions.ModuleToggleException;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

@ModuleData(
        name = "Click Gui",
        note = "A fancy user interface to customize modules",
        type = ModuleType.RENDER,
        keyCode = GLFW.GLFW_KEY_RIGHT_SHIFT
)
public class ClickGuiModule extends Module {

    private DropdownScreen dropdown;

    @Override
    public void onEnable() {
        Minecraft client = Minecraft.getInstance();

        if(dropdown == null) {
            this.dropdown = new DropdownScreen();
        }

        if(client.gui.screen() == null) {
            client.setScreenAndShow(this.dropdown);
        }

        throw new ModuleToggleException();
    }

}
