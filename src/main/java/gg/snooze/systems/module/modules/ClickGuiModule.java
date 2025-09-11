package gg.snooze.systems.module.modules;

import gg.snooze.ui.clickgui.clickguis.dropdown.DropdownScreen;
import gg.snooze.systems.module.Module;
import gg.snooze.systems.module.info.ModuleData;
import gg.snooze.systems.module.info.ModuleType;
import net.minecraft.client.MinecraftClient;
import org.lwjgl.glfw.GLFW;

@ModuleData(name = "Click Gui", type = ModuleType.MOVEMENT, keyCode = GLFW.GLFW_KEY_RIGHT_SHIFT)
public class ClickGuiModule extends Module {

    private DropdownScreen dropdown;

    @Override
    public boolean onEnable() {
        MinecraftClient client = MinecraftClient.getInstance();

        if(dropdown == null)
            this.dropdown = new DropdownScreen();

        if(client.currentScreen == null)
            client.setScreen(this.dropdown);

        return false;
    }

}
