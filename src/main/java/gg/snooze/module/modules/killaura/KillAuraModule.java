package gg.snooze.module.modules.killaura;

import gg.snooze.event.Listener;
import gg.snooze.event.events.PreUpdateEvent;
import gg.snooze.event.events.RotateEvent;
import gg.snooze.module.Module;
import gg.snooze.module.info.ModuleData;
import gg.snooze.module.info.ModuleType;
import gg.snooze.util.EntityUtil;
import gg.snooze.util.RotateUtil;
import gg.snooze.util.VectorUtil;
import gg.snooze.value.ValueFactory;
import gg.snooze.value.values.ModeValue;
import gg.snooze.value.values.MultiValue;
import gg.snooze.value.values.NumberValue;
import lombok.RequiredArgsConstructor;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.function.Function;

@ModuleData(
        name = "Kill Aura",
        note = "Automatically attack living enemies",
        type = ModuleType.COMBAT
)
public class KillAuraModule extends Module {

    private final ModeValue<SortMode> sortMode = ValueFactory.mode("Sort Mode", SortMode.values(), m -> m.name);
    private final ModeValue<VectorUtil.VectorType> vectorMode = ValueFactory.mode("Vector Mode", VectorUtil.VectorType.values(), m -> m.name);
    private final NumberValue scanningRange = ValueFactory.number("Scanning Range", 2.0, 8.0, 0.01);
    private final MultiValue targetTypes = ValueFactory.multi("Target Types", "Mobs", "Friendly", "Players", "Invisibles");

    private LivingEntity currentTarget;
    private Vec3 targetVector;

    @RequiredArgsConstructor
    enum SortMode {
        CLOSEST("Closest", e -> e.getEyePosition().distanceTo(mc.player.getEyePosition())),
        HURT_TIME("Hurt Time", e -> (double) ((LivingEntity) e).hurtTime);

        private final String name;
        private final Function<Entity, Double> valueFunction;
    }

    public KillAuraModule() {
        this.addListener(PreUpdateEvent.ID, onPreUpdate);
        this.addListener(RotateEvent.ID, onRotate);
    }

    private final Listener<PreUpdateEvent> onPreUpdate = event -> {
        this.currentTarget = this.getLivingEntity(this.sortMode.getValue().valueFunction);

        if(currentTarget == null) {
            this.targetVector = null;
            return;
        }
        // TODO: Click and autoblock
    };

    private final Listener<RotateEvent> onRotate = event -> {
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

        event.rotation.setX(rawRotations.x);
        event.rotation.setY(rawRotations.y);
    };

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
                    this.targetTypes.getValue("Mobs"),
                    this.targetTypes.getValue("Friendly"),
                    this.targetTypes.getValue("Players"),
                    false,
                    false,
                    false,
                    this.targetTypes.getValue("Invisibles")
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
