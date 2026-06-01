package gg.snooze.util;

import net.minecraft.util.Mth;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public final class VectorUtil {

    private VectorUtil() {
    }

    // TODO: Create and add type `Raycasting` and 'Closest to Look'.
    public enum VectorType {
        BASIC("Basic"),
        CLOSEST("Closest");

        public final String name;

        VectorType(String name) {
            this.name = name;
        }
    }

    public static Vec3 vector(Vec3 baseVector, Vec3 playerEyes, AABB area, VectorType type) {
        return switch(type) {
            case BASIC -> baseVector;
            case CLOSEST -> closest(playerEyes, area);
        };
    }

    public static Vec3 closest(Vec3 origin, AABB box) {
        double x = Mth.clamp(origin.x, box.minX, box.maxX);
        double y = Mth.clamp(origin.y, box.minY, box.maxY);
        double z = Mth.clamp(origin.z, box.minZ, box.maxZ);

        return new Vec3(x, y, z);
    }

}
