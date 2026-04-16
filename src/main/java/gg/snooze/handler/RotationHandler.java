package gg.snooze.handler;

import gg.snooze.Snooze;
import gg.snooze.event.events.RotateEvent;
import gg.snooze.util.Rotation;
import lombok.experimental.UtilityClass;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.util.Mth;

// TODO: E    X    P    A    N    D
@UtilityClass
public class RotationHandler {

    public Rotation previous, current;
    private double accumulatedYaw, accumulatedPitch;

    public void onTurnPlayer(float yaw, float pitch) {
        RotateEvent event = new RotateEvent(new Rotation(yaw, pitch));

        Snooze.INSTANCE.eventBus.postUnsafe(RotateEvent.ID, event);

        Rotation rotation = event.rotation;

        if(!rotation.isModified()) {
            previous = current;
            current = rotation;
            return;
        }

        previous = current;
        current = applySensitivityPatch(rotation);
    }

    private Rotation applySensitivityPatch(Rotation rotation) {
        double ss = Minecraft.getInstance().options.sensitivity().get() * (double) 0.6F + (double) 0.2F;
        double sensitivityMod = ss * ss * ss;
        double sens = sensitivityMod * (double) 8.0F;

        float deltaYaw = Mth.wrapDegrees(current.getYaw() - previous.getYaw());
        float deltaPitch = current.getPitch() - previous.getPitch();

        accumulatedYaw += deltaYaw;
        accumulatedPitch += deltaPitch;

        int xPixels = (int) (accumulatedYaw / sens);
        int yPixels = (int) (accumulatedPitch / sens);

        double xo = xPixels * sens;
        double yo = yPixels * sens;

        accumulatedYaw -= xo;
        accumulatedPitch -= yo;

        float xDelta = (float) yo * 0.15F;
        float yDelta = (float) xo * 0.15F;

        rotation.setYaw(current.getYaw() + xDelta);
        rotation.setPitch(current.getPitch() + yDelta);
        rotation.setPitch(Mth.clamp(rotation.getPitch(), -90.0F, 90.0F));

        LocalPlayer player = Minecraft.getInstance().player;

        if(player.getVehicle() != null) {
            player.getVehicle().onPassengerTurned(player);
        }

        return rotation;
    }

}
