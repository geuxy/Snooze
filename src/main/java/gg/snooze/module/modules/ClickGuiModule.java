package gg.snooze.module.modules;

import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;
import gg.snooze.ui.clickgui.ClickGuiScreen;
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

    private final ClickGuiScreen screen = new ClickGuiScreen();

    @Override
    public void onEnable() {
        Minecraft client = Minecraft.getInstance();

        if(client.screen == null) {
            client.setScreen(this.screen);
        }

        throw new ModuleToggleException();
    }

}
