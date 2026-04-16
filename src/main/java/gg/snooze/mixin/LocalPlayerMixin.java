package gg.snooze.mixin;

import gg.snooze.Snooze;
import gg.snooze.event.events.PreUpdateEvent;
import net.minecraft.client.player.LocalPlayer;
import org.lwjgl.system.CallbackI;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public class LocalPlayerMixin {

    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/AbstractClientPlayer;tick()V"))
    private void injectBeforeTick(CallbackInfo ci) {
        Snooze.INSTANCE.eventBus.postUnsafe(PreUpdateEvent.ID, new PreUpdateEvent());
    }

}
