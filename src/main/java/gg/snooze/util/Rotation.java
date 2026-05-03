package gg.snooze.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter @AllArgsConstructor
public class Rotation {

    private float x, y;
    private boolean modified;

    public Rotation(float x, float y) {
        this.x = x;
        this.y = y;
        this.modified = false;
    }

    public Rotation getDelta(Rotation oldRotation) {
        return new Rotation(this.x - oldRotation.x, this.y - oldRotation.y);
    }

    public void setX(float x) {
        this.x = x;
        this.modified = true;
    }

    public void setY(float y) {
        this.y = y;
        this.modified = true;
    }

}
