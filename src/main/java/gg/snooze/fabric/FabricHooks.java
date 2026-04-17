package gg.snooze.fabric;

import gg.snooze.Snooze;
import gg.snooze.event.events.RenderHudEvent;
import net.fabricmc.fabric.api.client.rendering.v1.hud.HudElementRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.hud.VanillaHudElements;
import net.minecraft.resources.Identifier;

public class FabricHooks {

    public static void attachHud(Identifier identifier) {
        HudElementRegistry.attachElementBefore(VanillaHudElements.CHAT, identifier, (g, t) ->
                Snooze.INSTANCE.eventBus.postUnsafe(RenderHudEvent.ID, new RenderHudEvent(g, t))
        );
    }

}
