package gg.snooze.ui.clickgui;

import gg.snooze.module.info.ModuleType;
import gg.snooze.ui.BaseScreen;
import gg.snooze.ui.clickgui.style.ClickStyle;
import gg.snooze.ui.clickgui.style.ClickStyleImpl;
import gg.snooze.ui.core.drag.DragHandler;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.input.MouseButtonEvent;

import java.util.List;
import java.util.stream.Stream;

public class ClickGuiScreen extends BaseScreen {

    private static final int FRAME_WIDTH = 110;
    private static final int FRAME_HEIGHT = 16;
    private static final int FRAME_OFFSET = 4;
    private static final int MODULE_HEIGHT = 16;

    private final List<ClickGuiFrame> frames;

    private final DragHandler dragHandler;
    private final ClickStyle style;

    public ClickGuiScreen() {
        super();
        this.frames = Stream.of(ModuleType.values())
                .map(this::createFrame)
                .toList();
        this.dragHandler = new DragHandler();
        this.style = new ClickStyleImpl();
    }

    @Override
    public void onClose() {
        super.onClose();
        this.dragHandler.release(0);
    }

    @Override
    public void render(GuiGraphicsExtractor graphics) {
        this.style.setGraphics(graphics);
        this.frames.forEach(f -> f.render(style));
    }

    @Override
    public boolean click(MouseButtonEvent event) {
        return dragHandler.click(this.frames, event)
                || this.frames.stream().anyMatch(f -> f.click(event));
    }

    @Override
    public boolean release(MouseButtonEvent event) {
        return dragHandler.click(this.frames, event)
                || this.frames.stream().anyMatch(f -> f.release(event));
    }

    private ClickGuiFrame createFrame(ModuleType type) {
        var frame = new ClickGuiFrame(type, MODULE_HEIGHT);
        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        frame.setPosition(FRAME_OFFSET + (type.ordinal() * (frame.width + FRAME_HEIGHT)), FRAME_HEIGHT);
        return frame;
    }

}
