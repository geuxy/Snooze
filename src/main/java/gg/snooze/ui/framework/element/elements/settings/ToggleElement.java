package gg.snooze.ui.framework.element.elements.settings;

import gg.snooze.setting.settings.BooleanSetting;
import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.event.events.MouseClickEvent;
import org.jetbrains.annotations.NotNull;

public class ToggleElement extends BaseElement {

    @NotNull
    private final Controller controller;

    public interface Controller {

        void toggle();
        boolean isEnabled();

        default String getName() {
            return "";
        }
    }

    public ToggleElement(BooleanSetting setting) {
        this(new Controller() {

            @Override
            public void toggle() {
                setting.toggle();
            }

            @Override
            public boolean isEnabled() {
                return setting.getValue();
            }
        });
    }

    public ToggleElement(@NotNull Controller controller) {
        this.controller = controller;
        this.listen(MouseClickEvent.ID, e -> {
            MouseClickEvent event = (MouseClickEvent) e;

            if (!event.isReleased() && event.getButton() == 0 && isMouseAt(event.getMouseX(), event.getMouseY())) {
                this.controller.toggle();
            }
        });
    }

    @Override
    public void render(int mouseX, int mouseY) {
        if (this.getStyle() != null) {
            this.getStyle().drawToggle(this, mouseX, mouseY);
        }
    }

}
