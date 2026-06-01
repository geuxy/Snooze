package gg.snooze.ui.framework.layout;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.element.elements.BaseContainerElement;

import java.util.List;

@FunctionalInterface
public interface Layout {

    void updateElements(BaseContainerElement parent, List<BaseElement> children);

}
