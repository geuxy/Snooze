package gg.snooze.ui.clickgui;

import gg.snooze.ui.BaseScreen;
import gg.snooze.ui.framework.element.elements.ButtonElement;
import gg.snooze.ui.framework.element.elements.containers.PanelElement;
import gg.snooze.ui.framework.element.elements.containers.SceneElement;
import gg.snooze.ui.framework.layout.impl.VerticalLayout;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import org.jspecify.annotations.NonNull;

public class ClickGuiScreen extends BaseScreen {

    private final ClickGuiStyle style = new ClickGuiStyle();

    @Override
    public SceneElement createScene() {
        var scene = new SceneElement();
        var pane = new PanelElement();
        var child = new ButtonElement();

        var paneLayout = new VerticalLayout();
        paneLayout.setPadding(10);

        scene.setLayout(new VerticalLayout());
        pane.setLayout(paneLayout);

        pane.setSize(120, 60);
        pane.setPreferredSize(120, 60);
        child.setSize(100, 20);
        child.setPreferredSize(100, 20);

        pane.addChild(child);
        scene.addChild(pane);

        scene.setStyle(style);
        pane.setStyle(style);
        child.setStyle(style);

        return scene;
    }

    @Override
    public void extractRenderState(@NonNull GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        this.style.setGraphics(graphics);
        super.extractRenderState(graphics, mouseX, mouseY, a);
    }

}
