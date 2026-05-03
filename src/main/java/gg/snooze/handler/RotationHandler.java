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

    public Rotation previous, current, delta, previousDelta;
    private double accumulatedYaw, accumulatedPitch;

    public void initializeRotation(float x, float y) {
        current = new Rotation(x, y);
        delta = new Rotation(0, 0);
        previous = current;
        previousDelta = delta;
        accumulatedYaw = 0;
        accumulatedPitch = 0;
    }

    public boolean onTurnPlayer(float yaw, float pitch) {
        RotateEvent event = new RotateEvent(new Rotation(yaw, pitch));
        Snooze.INSTANCE.eventBus.postUnsafe(RotateEvent.ID, event);

        Rotation rotation = event.rotation;

        if(!rotation.isModified()) {
            update(rotation);
            return false;
        }

        update(rotation);
        return true;
    }

    private void update(Rotation newRotation) {
        previous = current;
        previousDelta = delta;

        current = newRotation;
        delta = new Rotation(current.getX() - previous.getX(), current.getY() - previous.getY());
        delta.setX(Mth.wrapDegrees(delta.getX()));
        delta.setY(Mth.clamp(delta.getY(), -90.0F, 90.0F));
    }

    /*private Rotation applySensitivityPatch(Rotation rotation) {
        double ss = Minecraft.getInstance().options.sensitivity().get() * (double) 0.6F + (double) 0.2F;
        double sensitivityMod = ss * ss * ss;
        double sens = sensitivityMod * (double) 8.0F;

        float deltaYaw = Mth.wrapDegrees(current.getX() - previous.getX());
        float deltaPitch = current.getY() - previous.getY();

        accumulatedYaw += deltaYaw;
        accumulatedPitch += deltaPitch;

        int xPixels = (int) (accumulatedYaw / sens);
        int yPixels = (int) (accumulatedPitch / sens);

        double xo = xPixels * sens;
        double yo = yPixels * sens;

        accumulatedYaw -= xo;
        accumulatedPitch -= yo;

        float xDelta = (float) xo * 0.15F;
        float yDelta = (float) yo * 0.15F;

        rotation.setX(current.getX() + xDelta);
        rotation.setY(current.getY() + yDelta);
        rotation.setY(Mth.clamp(rotation.getY(), -90.0F, 90.0F));

        LocalPlayer player = Minecraft.getInstance().player;

        if(player.getVehicle() != null) {
            player.getVehicle().onPassengerTurned(player);
        }

        return rotation;
    }*/

}
