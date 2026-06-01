package gg.snooze.ui.framework.layout.impl;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.element.elements.containers.ScrollPaneElement;
import gg.snooze.ui.framework.layout.Layout;

import java.util.List;

public class VerticalLayout implements Layout {

    public Alignment alignmentX, alignmentY;
    public int padding, spacing, alignmentXOffset, alignmentYOffset;
    public boolean resizeWidth, resizeParentWidth, resizeParentHeight;
    private int totalHeight;

    public enum Alignment {
        START, CENTER, END
    }

    public VerticalLayout() {
        this.alignmentX = Alignment.CENTER;
        this.alignmentY = Alignment.CENTER;
        this.resizeWidth = true;
        this.resizeParentWidth = true;
        this.resizeParentHeight = true;
    }

    @Override
    public void updateElements(BaseContainerElement<?> parent, List<BaseElement> children) {
        int childHeight = calculateChildrenHeight(children);
        int maxChildWidth = calculateMaxChildWidth(children);

        totalHeight = childHeight + (padding * 2);

        if(parent.isResizable()) {
            if(resizeParentHeight) {
                parent.height = totalHeight;
            }

            if(resizeParentWidth) {
                parent.width = maxChildWidth + (padding * 2);
            }
        }

        int childY = resolveStartChildY(parent, children);
        int startChildY = childY;

        // TODO: change to something less stupid
        if (parent instanceof ScrollPaneElement pane) {
            int scroll = pane.value;

            childY += scroll;
            startChildY += scroll;
        }

        for (BaseElement child : children) {
            int childWidth = resolveChildWidth(parent, child);

            child.width = childWidth;
            child.x = resolveChildX(parent, child);
            child.y = childY;

            childY += child.height + spacing;
        }

        childY -= spacing;
        totalHeight = childY - startChildY + (padding * 2);
    }

    private int calculateMaxChildWidth(List<BaseElement> children) {
        int max = 0;
        for (BaseElement child : children) {
            int w = child.preferredWidth > 0
                    ? child.preferredWidth
                    : child.width;
            max = Math.max(max, w);
        }
        return max;
    }

    private int calculateChildrenHeight(List<BaseElement> children) {
        if(children.isEmpty()) {
            return 0;
        }

        int sum = 0;

        for (BaseElement child : children) {
            sum += child.height + spacing;
        }

        return sum - spacing;
    }

    private int resolveChildX(BaseElement parent, BaseElement child) {
        return switch(alignmentX) {
            case START -> parent.x + padding;
            case END -> parent.x + parent.width - child.width - padding;
            default -> parent.x + (parent.width / 2) - (child.width / 2);

        } + alignmentXOffset;
    }

    private int resolveStartChildY(BaseElement parent, List<BaseElement> children) {
        int childrenHeight = this.calculateChildrenHeight(children);

        return switch(alignmentY) {
            case Alignment.START -> parent.y + padding;
            case Alignment.CENTER -> parent.y + (parent.height - childrenHeight) / 2;
            case Alignment.END -> parent.y + parent.height - childrenHeight - padding;

        } + alignmentYOffset;
    }

    private int resolveChildWidth(BaseElement parent, BaseElement child) {
        int maxWidth = parent.width - (padding * 2);
        int preferredWidth = child.preferredWidth;

        return preferredWidth > 0 ? Math.min(preferredWidth, maxWidth) : child.width;
    }

    public int getTotalHeight() {
        return totalHeight;
    }

}
