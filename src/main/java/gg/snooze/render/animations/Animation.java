package gg.snooze.render.animations;

import gg.snooze.render.animations.easing.EasingFunction;
import gg.snooze.render.animations.easing.Easings;
import gg.snooze.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
public class Animation {

    @Setter
    private float value, duration;
    private float fromValue, toValue, elapsed;
    private boolean finished = false;

    @Setter
    private EasingFunction easing = Easings.NONE;

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
        if (finished) return value;

        elapsed += TimeUtil.DELTA_TIME;

        float elapsedTime = Math.min(elapsed / duration, 1);
        float easeResult = easing.process(elapsedTime);

        value = interpolate(fromValue, toValue, easeResult);

        if (elapsedTime >= 1) finished = true;

        return value;
    }

    public void finish() {
        this.value = toValue;
        this.finished = true;
    }

    public void setToValue(float newToVal) {
        if(newToVal == toValue) {
            return;
        }

        this.fromValue = value;
        this.toValue = newToVal;
        this.elapsed = 0;
        this.finished = false;
    }

    public float interpolate(float start, float end, float pct) {
        return start + (end - start) * pct;
    }

}
