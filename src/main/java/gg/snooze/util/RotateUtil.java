package gg.snooze.util;

import lombok.experimental.UtilityClass;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

@UtilityClass
public class RotateUtil {

    public Vec2 getRotations(Vec3 origin, Vec3 target) {
        double xDiff = target.x - origin.x;
        double yDiff = target.y - origin.y;
        double zDiff = target.z - origin.z;

        double distance = Math.sqrt(xDiff * xDiff + zDiff * zDiff);

        float yaw = (float) ((Math.atan2(zDiff, xDiff) * 180 / Math.PI) - 90);
        float pitch = (float) (-(Math.atan2(yDiff, distance) * 180 / Math.PI));

        return new Vec2(yaw, pitch);
    }

}