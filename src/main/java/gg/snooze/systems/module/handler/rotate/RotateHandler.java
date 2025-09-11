package gg.snooze.systems.module.handler.rotate;

import gg.snooze.systems.module.Module;
import lombok.NonNull;
import net.minecraft.util.math.Vec3d;

public class RotateHandler {

    private RotateMode mode;

    public RotateHandler(@NonNull RotateMode defaultMode, Module module) {
        this.mode = defaultMode;

        // Register rotate properties here...
    }

    public void onAngle(Vec3d targetVec) {
        if (targetVec != null) {
            this.mode.rotate(targetVec);
        }
    }

}
