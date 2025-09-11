package gg.snooze.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public static float interpolate(float start, float end, float pct) {
        return start + (end - start) * pct;
    }

}
