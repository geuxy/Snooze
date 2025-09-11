package gg.snooze.util;

import lombok.experimental.UtilityClass;
import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;

@UtilityClass
public class RotateUtil {

    public Vec2f getRotations(Vec3d origin, Vec3d target) {
        double xDiff = target.x - origin.x;
        double yDiff = target.y - origin.y;
        double zDiff = target.z - origin.z;

        double distance = Math.sqrt(xDiff * xDiff + zDiff * zDiff);

        float yaw = (float) ((Math.atan2(zDiff, xDiff) * 180 / Math.PI) - 90);
        float pitch = (float) (-(Math.atan2(yDiff, distance) * 180 / Math.PI));

        return new Vec2f(yaw, pitch);
    }

    public Vec2f applySensitivity(Vec2f currentRots, Vec2f previousRots) {
        MinecraftClient client = MinecraftClient.getInstance();
        double mouseSens = client.options.getMouseSensitivity().getValue();

        double sens = mouseSens * (double) 0.6F + (double) 0.2F;
        double gcd = (sens * sens * sens) * (double) 8.0F;

        double cursorDeltaX = wrap(currentRots.x - previousRots.x);
        double cursorDeltaY = currentRots.y - previousRots.y;

        float fixedDeltaYaw = (float) (cursorDeltaX * gcd) * 0.15F;
        float fixedDeltaPitch = (float) (cursorDeltaY * gcd) * 0.15F;

        return new Vec2f(previousRots.x + fixedDeltaYaw, previousRots.y + fixedDeltaPitch);
    }

    public float wrap(float yaw) {
        return (((yaw % 360F) + 540) % 360) - 180;
    }

}