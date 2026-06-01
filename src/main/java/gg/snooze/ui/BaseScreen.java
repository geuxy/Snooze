package gg.snooze.ui;

import gg.snooze.ui.framework.element.elements.containers.SceneElement;
import gg.snooze.ui.framework.event.events.MouseClickEvent;
import gg.snooze.ui.framework.event.events.MouseScrollEvent;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import org.jspecify.annotations.NonNull;

public class BaseScreen extends Screen {

    private SceneElement scene;

    private int mouseX, mouseY;

    protected BaseScreen() {
        super(Component.empty());
        this.scene = createScene();
    }

    public SceneElement createScene() {
        return new SceneElement();
    }

    @Override
    protected void init() {
        super.init();
        this.scene.resizeScene(width, height);
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        this.mouseX = mouseX;
        this.mouseY = mouseY;

        //super.extractRenderState(graphics, mouseX, mouseY, a);
        this.scene.render(mouseX, mouseY);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        this.scene.resizeScene(width, height);
    }

    @Override
    public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
        //return super.mouseClicked(event, doubleClick);
        return mouse(event, false);
    }

    @Override
    public boolean mouseReleased(MouseButtonEvent event) {
        //return super.mouseReleased(event);
        return mouse(event, true);
    }

    @Override
    public boolean mouseScrolled(double x, double y, double scrollX, double scrollY) {
        //return super.mouseScrolled(x, y, scrollX, scrollY);
        MouseScrollEvent mouseScrollEvent = new MouseScrollEvent((int) Math.signum(scrollY), mouseX, mouseY);

        this.scene.invoke(MouseScrollEvent.ID, mouseScrollEvent);

        return mouseScrollEvent.isConsumed();
    }

    private boolean mouse(MouseButtonEvent event, boolean released) {
        MouseClickEvent mouseClickEvent = new MouseClickEvent(
                event.button(),
                released,
                mouseX,
                mouseY
        );

        this.scene.invoke(MouseClickEvent.ID, mouseClickEvent);

        return mouseClickEvent.isConsumed();
    }

}
