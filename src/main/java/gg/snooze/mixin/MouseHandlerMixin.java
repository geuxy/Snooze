package gg.snooze.mixin;

import gg.snooze.handler.RotationHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.client.MouseHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(MouseHandler.class)
public class MouseHandlerMixin {

    @ModifyArgs(method = "turnPlayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/LocalPlayer;turn(DD)V"))
    private void modifyTurnPlayer(Args args) {
        if(Minecraft.getInstance().player == null) {
            return;
        }

        Double xArg = args.get(0);
        Double yArg = args.get(1);

        float xDelta = xArg.floatValue() * 0.15F;
        float yDelta = yArg.floatValue() * 0.15F;
        float newYaw = Minecraft.getInstance().player.getYRot() + xDelta;
        float newPitch = Minecraft.getInstance().player.getXRot() + yDelta;

        if(RotationHandler.onTurnPlayer(newYaw, newPitch)) {
            args.set(0, (double) (RotationHandler.delta.getX() / 0.15F));
            args.set(1, (double) (RotationHandler.delta.getY() / 0.15F));
        }
    }

}
