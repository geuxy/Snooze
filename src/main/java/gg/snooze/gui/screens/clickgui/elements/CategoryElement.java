package gg.snooze.gui.screens.clickgui.elements;

import gg.snooze.gui.elements.UIElement;
import gg.snooze.module.info.ModuleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class CategoryElement extends UIElement {

    private final ModuleType type;

}
