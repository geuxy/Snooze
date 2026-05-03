package gg.snooze.util;

import lombok.experimental.UtilityClass;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.hurtingprojectile.Fireball;
import net.minecraft.world.phys.AABB;

@UtilityClass
public class EntityUtil {

    public boolean filter(Entity entity, boolean mobs, boolean friendly, boolean players, boolean fireballs, boolean projectiles, boolean items, boolean invisibles) {
        return (entity instanceof Mob && mobs) ||
                ((entity instanceof Animal || entity instanceof Villager) && friendly) ||
                (entity instanceof Player && players) ||
                (entity instanceof Fireball && fireballs) ||
                (entity instanceof Projectile && projectiles) ||
                (entity instanceof ItemEntity && items)
                && (!entity.isInvisible() || invisibles);
    }

    public AABB lerpBoundingBox(float partialTicks, Entity entity) {
        double smoothX = Mth.lerp(partialTicks, entity.xo, entity.getX());
        double smoothY = Mth.lerp(partialTicks, entity.yo, entity.getY());
        double smoothZ = Mth.lerp(partialTicks, entity.zo, entity.getZ());

        EntityDimensions dimensions = entity.getDimensions(entity.getPose());

        return dimensions.makeBoundingBox(smoothX, smoothY, smoothZ);
    }

}
