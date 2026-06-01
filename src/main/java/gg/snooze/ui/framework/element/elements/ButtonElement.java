package gg.snooze.ui.framework.element.elements;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.event.events.MouseClickEvent;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ButtonElement extends BaseElement {

    public Supplier<String> textSupplier;

    public ButtonElement() {
        this.listen(MouseClickEvent.ID, e -> {
            MouseClickEvent event = (MouseClickEvent) e;

            if(event.getButton() == 0 && !event.isReleased() && isMouseAt(event.getMouseX(), event.getMouseY())) {
                System.out.println("Button clicked!");
            }
        });
    }

    @Override
    public void render(@NotNull ElementStyle style) {
        style.drawButton(this);
    }

    public String getText() {
        return this.textSupplier.get();
    }

}
