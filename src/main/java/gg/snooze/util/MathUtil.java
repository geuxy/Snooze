package gg.snooze.util;

public final class MathUtil {

    private MathUtil() {
    }

    public static float lerp(float start, float end, float pct) {
        return start + (end - start) * pct;
    }

}
