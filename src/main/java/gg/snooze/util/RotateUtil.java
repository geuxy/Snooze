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

    public Vec2f applySensitivity(Vec2f rotations, Vec2f lastRotations) {
        MinecraftClient client = MinecraftClient.getInstance();

        double sens = client.options.getMouseSensitivity().getValue() * (double)0.6F + (double)0.2F;
        double step = sens * sens * sens;
        double gcd = step * (double)8.0F;

        double cursorDeltaX = ((((rotations.x - lastRotations.x) % 360F) + 540) % 360) - 180;
        double cursorDeltaY = rotations.y - lastRotations.y;

        double fixedDeltaX = cursorDeltaX * gcd;
        double fixedDeltaY = cursorDeltaY * gcd;

        float g = (float)fixedDeltaX * 0.15F;
        float h = (float)fixedDeltaY * 0.15F;

        return new Vec2f(lastRotations.x + g, lastRotations.y + h);
    }

}