package gg.snooze.mixin;

import gg.snooze.handler.RotationHandler;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public class EntityMixin {

    @Shadow
    private float xRot, yRot;

    @Inject(method = "turn", at = @At("RETURN"))
    private void injectTurn(CallbackInfo ci) {
        RotationHandler.onTurnPlayer(xRot, yRot);
    }

}
