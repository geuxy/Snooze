package gg.snooze.systems.module.handler.rotate;

import net.minecraft.util.math.Vec3d;

public abstract class RotateMode {

    public abstract void onEnable();
    public abstract void onDisable();
    public abstract void rotate(Vec3d targetVec);

}
