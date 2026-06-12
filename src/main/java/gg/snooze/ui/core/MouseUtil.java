package gg.snooze.ui.core;

public final class MouseUtil {

    private MouseUtil() {
    }

    public static boolean isAt(Region region, double mouseX, double mouseY) {
        return mouseX >= region.x && mouseX <= region.x + region.width
                && mouseY >= region.y && mouseY <= region.y + region.height;
    }

    public static boolean isAt(double mouseX, double mouseY, int x, int y, int width, int height) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + height;
    }

}
