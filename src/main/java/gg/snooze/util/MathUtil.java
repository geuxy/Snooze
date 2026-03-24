package gg.snooze.util;

import lombok.experimental.UtilityClass;
import net.minecraft.util.Mth;

@UtilityClass
public class MathUtil {

    public static float lerp_float(float start, float end, float pct) {
        return start + (end - start) * pct;
    }

    public static double lerp_double(double start, double end, double pct) {
        return start + (end - start) * pct;
    }

    public static int lerp_int(int start, int end, double pct) {
        return Mth.floor(start + (end - start) * pct);
    }

}
