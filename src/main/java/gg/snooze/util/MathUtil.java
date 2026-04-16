package gg.snooze.util;

import lombok.experimental.UtilityClass;

@UtilityClass
public class MathUtil {

    public static float lerp(float start, float end, float pct) {
        return start + (end - start) * pct;
    }

}
