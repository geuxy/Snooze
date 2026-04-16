package gg.snooze.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Rotation {

    private float yaw, pitch;
    private boolean modified;

    public Rotation(float yaw, float pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
        this.modified = false;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
        this.modified = true;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
        this.modified = true;
    }

}
