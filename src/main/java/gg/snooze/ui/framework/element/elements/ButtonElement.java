package gg.snooze.ui.framework.element.elements;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.style.ElementStyle;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ButtonElement extends BaseElement {

    public Supplier<String> textSupplier;

    @Override
    public void render(@NotNull ElementStyle style) {
        style.drawButton(this);
    }

    public String getText() {
        return this.textSupplier.get();
    }

}
