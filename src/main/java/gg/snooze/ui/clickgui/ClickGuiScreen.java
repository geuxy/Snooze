package gg.snooze.ui.clickgui;

import gg.snooze.ui.BaseScreen;
import gg.snooze.ui.framework.element.elements.ButtonElement;
import gg.snooze.ui.framework.element.elements.containers.PanelElement;
import gg.snooze.ui.framework.element.elements.containers.SceneElement;
import gg.snooze.ui.framework.layout.impl.VerticalLayout;

public class ClickGuiScreen extends BaseScreen {

    public ClickGuiScreen() {
        var scene = new SceneElement();
        var pane = new PanelElement();
        var child = new ButtonElement();

        var paneLayout = new VerticalLayout();
        paneLayout.padding = 10;

        scene.setLayout(new VerticalLayout());
        pane.setLayout(paneLayout);

        pane.setSize(120, 60);
        child.setSize(100, 20);
        pane.setPreferredSize(120, 60);
        child.setPreferredSize(100, 20);

        pane.addChild(child);
        scene.addChild(pane);

        super(scene, new ClickGuiStyle());
    }

}
