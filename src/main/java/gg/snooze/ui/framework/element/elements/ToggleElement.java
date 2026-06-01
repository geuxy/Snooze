package gg.snooze.ui.framework.element.elements;

import gg.snooze.setting.settings.BooleanSetting;
import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.event.events.MouseClickEvent;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

public class ToggleElement extends BaseElement {

    @NotNull
    private final Controller controller;

    public interface Controller {

        void toggle();
        boolean getValue();

        default String getName() {
            return "";
        }
    }

    public ToggleElement(BooleanSetting setting) {
        this(new Controller() {
            @Override public void toggle() {setting.toggle();}
            @Override public boolean getValue() {return setting.getValue();}
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
    public void render(@NotNull ElementStyle style) {
        style.drawToggle(this);
    }

    public boolean isEnabled() {
        return this.controller.getValue();
    }

}
