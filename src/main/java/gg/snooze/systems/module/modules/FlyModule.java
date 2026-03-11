package gg.snooze.systems.module.modules;

import gg.snooze.core.Snooze;
import gg.snooze.systems.event.Listener;
import gg.snooze.systems.event.events.UpdateEvent;
import gg.snooze.systems.module.Module;
import gg.snooze.systems.module.info.ModuleData;
import gg.snooze.systems.module.info.ModuleType;
import net.minecraft.client.Minecraft;
import org.lwjgl.glfw.GLFW;

@ModuleData(name = "Fly", note = "Fly like a bird", type = ModuleType.MOVEMENT, keyCode = GLFW.GLFW_KEY_R)
public class FlyModule extends Module {

    @Override
    public boolean onEnable() {
        Snooze.INSTANCE.eventBus().subscribe(UpdateEvent.ID, onUpdate);
        return super.onEnable();
    }

    @Override
    public boolean onDisable() {
        Snooze.INSTANCE.eventBus().unsubscribe(UpdateEvent.ID, onUpdate);
        return super.onDisable();
    }

    private final Listener<UpdateEvent> onUpdate = event -> {
        Minecraft client = Minecraft.getInstance();

        if(client.player != null) {
            client.player.getAbilities().flying = true;
        }
    };

}
