package gg.snooze.ui.clickgui.elements;

import gg.snooze.ui.framework.UIElement;
import gg.snooze.module.info.ModuleType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter @RequiredArgsConstructor
public class CategoryElement extends UIElement {

    private final ModuleType type;

}
