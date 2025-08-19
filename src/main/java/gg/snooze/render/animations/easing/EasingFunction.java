package gg.snooze.render.animations.easing;

@FunctionalInterface
public interface EasingFunction {

    float process(float v);

}
