package gg.snooze.module.modules.killaura;

import gg.snooze.event.events.PreUpdateEvent;
import gg.snooze.event.events.RotateEvent;
import gg.snooze.event.listeners.PreUpdateListener;
import gg.snooze.event.listeners.RotateListener;
import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;
import gg.snooze.setting.settings.option.ModeSetting;
import gg.snooze.setting.settings.option.MultiSetting;
import gg.snooze.util.EntityUtil;
import gg.snooze.util.RotateUtil;
import gg.snooze.util.VectorUtil;
import gg.snooze.setting.SettingFactory;
import gg.snooze.setting.settings.NumberSetting;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Function;

@ModuleData(
        name = "Kill Aura",
        note = "Automatically attack living enemies",
        type = ModuleType.COMBAT,
        events = {PreUpdateEvent.ID, RotateEvent.ID}
)
public class KillAuraModule extends Module implements PreUpdateListener, RotateListener {

    private final ModeSetting<Function<Entity, Double>> sortMode = SettingFactory.<Function<Entity, Double>>mode("Sort Mode")
            .addOption("Closest", e -> e.getEyePosition().distanceTo(mc.player.getEyePosition()))
            .addOption("Hurt Time", e -> (double) ((LivingEntity) e).hurtTime);

    private final ModeSetting<VectorUtil.VectorType> vectorMode = SettingFactory.<VectorUtil.VectorType>mode("Vector Mode")
            .addOption("Basic", VectorUtil.VectorType.BASIC)
            .addOption("Closest", VectorUtil.VectorType.CLOSEST);

    private final NumberSetting scanningRange = SettingFactory.number("Scanning Range", 2.0, 8.0, 0.01);
    private final MultiSetting<Boolean> targetTypes = SettingFactory.<Boolean>multi("Target Types")
            .addOption("Mobs", false)
            .addOption("Friendlies", false)
            .addOption("Players", false)
            .addOption("Invisibles", false);

    private LivingEntity currentTarget;
    private Vec3 targetVector;

    @Override
    public void onPreUpdate(PreUpdateEvent event) {
        this.currentTarget = this.getLivingEntity(this.sortMode.getValue());

        if(currentTarget == null) {
            this.targetVector = null;
            return;
        }
        // TODO: Click and autoblock
    }

    @Override
    public void onRotate(RotateEvent event) {
        if(mc.player == null || currentTarget == null) {
            return;
        }

        float partialTicks = mc.getDeltaTracker().getGameTimeDeltaPartialTick(false);

        Vec3 targetEyes = this.currentTarget.getEyePosition(partialTicks);
        Vec3 playerEyes = mc.player.getEyePosition(partialTicks);

        this.targetVector = VectorUtil.vector(
                targetEyes,
                playerEyes,
                EntityUtil.lerpBoundingBox(partialTicks, this.currentTarget),
                this.vectorMode.getValue()
        );

        Vec2 rawRotations = RotateUtil.getRotations(playerEyes, this.targetVector);

        event.rotation().setX(rawRotations.x);
        event.rotation().setY(rawRotations.y);
    }

    private LivingEntity getLivingEntity(Function<Entity, Double> valueFunction) {
        Entity bestEntity = null;
        double bestValue = Double.MAX_VALUE;

        if(mc.player == null || mc.level == null) {
            return null;
        }

        double range = this.scanningRange.getValue() * 2.0; // AABB will half `range` for each coord
        AABB box = AABB.ofSize(mc.player.getEyePosition(), range, range, range);

        List<Entity> entities = mc.level.getEntities(mc.player, box);

        for(Entity entity : entities) {
            if(!EntityUtil.filter(
                    entity,
                    this.targetTypes.isSelected(0),
                    this.targetTypes.isSelected(1),
                    this.targetTypes.isSelected(2),
                    false,
                    false,
                    false,
                    this.targetTypes.isSelected(4)
            )) {
                continue;
            }

            double value = valueFunction.apply(entity);

            if(bestEntity == null || bestValue > value) {
                bestEntity = entity;
                bestValue = value;
            }
        }

        return (LivingEntity) bestEntity;
    }

}
