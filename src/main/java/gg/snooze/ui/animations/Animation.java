package gg.snooze.ui.animations;

import gg.snooze.ui.animations.easing.EasingFunction;
import gg.snooze.ui.animations.easing.Easing;
import gg.snooze.util.MathUtil;
import gg.snooze.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Animation {

    @Setter
    private float value, duration;
    private float fromValue, destination, elapsed;
    private boolean finished = false;
    private EasingFunction easing = Easing.NONE;

    public Animation(float value, float duration) {
        this.value = value;
        this.fromValue = value;
        this.duration = duration;
    }

    public Animation(float value, float duration, EasingFunction easing) {
        this.value = value;
        this.fromValue = value;
        this.duration = duration;

        if(easing != null) {
            this.easing = easing;
        }
    }

    public float animate() {
        if (finished) {
            return value;
        }

        elapsed += TimeUtil.DELTA_TIME;

        float elapsedTime = Math.min(elapsed / duration, 1);
        float easeResult = easing.ease(elapsedTime);

        value = MathUtil.interpolate(fromValue, destination, easeResult);

        if (elapsedTime >= 1) {
            finished = true;
        }

        return value;
    }

    public void finish() {
        this.value = destination;
        this.finished = true;
    }

    public void setDestination(float newDestination) {
        if(newDestination == destination) {
            return;
        }

        this.fromValue = value;
        this.destination = newDestination;
        this.elapsed = 0;
        this.finished = false;
    }

    public void setEasing(EasingFunction easing) {
        if (easing != null) {
            this.easing = easing;
        }
    }

}
