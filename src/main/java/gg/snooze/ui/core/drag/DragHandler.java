package gg.snooze.ui.core.drag;

import gg.snooze.ui.core.Region;
import net.minecraft.client.input.MouseButtonEvent;

import java.util.List;

public class DragHandler {

    private Region draggingRegion;
    private int dragX, dragY;

    public void moveDragging(int mouseX, int mouseY) {
        if(this.draggingRegion != null) {
            this.draggingRegion.setPosition(
                    mouseX - this.dragX,
                    mouseY - this.dragY
            );
        }
    }

    public boolean click(List<? extends Region> regions, MouseButtonEvent event) {
        if(event.button() != 0) {
            return false;
        }

        for(int i = regions.size() - 1; i >= 0; i--) {
            var region = regions.get(i);
            int mouseX = (int) Math.round(event.x());
            int mouseY = (int) Math.round(event.y());

            if(region.canDrag(mouseX, mouseY)) {
                this.draggingRegion = region;
                this.dragX = mouseX - this.draggingRegion.x;
                this.dragY = mouseY - this.draggingRegion.y;
                return true;
            }
        }
        return false;
    }

    public boolean release(int state) {
        if(draggingRegion != null) {
            this.draggingRegion = null;
            this.dragX = 0;
            this.dragY = 0;
            return true;
        }
        return false;
    }

}
