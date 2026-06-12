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
        type = ModuleType.MOVEMENT,
        keyCode = GLFW.GLFW_KEY_RIGHT_SHIFT
)
public class ClickGuiModule extends Module {

    private ClickGuiScreen screen;

    @Override
    public void onEnable() {
        Minecraft client = Minecraft.getInstance();

        if(client.screen == null) {
            if(screen == null) {
                this.screen = new ClickGuiScreen();
            }

            client.setScreen(this.screen);
        }

        throw new ModuleToggleException();
    }

}
