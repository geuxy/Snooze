package gg.snooze.ui.framework.layout.impl;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.element.elements.containers.ScrollPaneElement;
import gg.snooze.ui.framework.layout.Layout;

import java.util.List;

public class VerticalLayout implements Layout {

    private Alignment alignmentX = Alignment.CENTER;
    private Alignment alignmentY = Alignment.CENTER;

    private int padding;
    private int spacing;
    private int alignmentXOffset;
    private int alignmentYOffset;
    private int totalHeight;

    private boolean resizeWidth = true;
    private boolean resizeParentWidth = true;
    private boolean resizeParentHeight = true;

    public enum Alignment {
        START, CENTER, END
    }

    @Override
    public void updateElements(BaseContainerElement<?> parent, List<BaseElement> children) {
        int childHeight = calculateChildrenHeight(children);
        int maxChildWidth = calculateMaxChildWidth(children);

        totalHeight = childHeight + (padding * 2);

        if(parent.isResizable()) {
            if(resizeParentHeight) {
                parent.setHeight(totalHeight);
            }

            if(resizeParentWidth) {
                parent.setWidth(maxChildWidth + (padding * 2));
            }
        }

        int childY = resolveStartChildY(parent, children);
        int startChildY = childY;

        // TODO: change to something less stupid
        if (parent instanceof ScrollPaneElement pane) {
            int scroll = pane.getValue();

            childY += scroll;
            startChildY += scroll;
        }

        for (BaseElement child : children) {
            int childWidth = resolveChildWidth(parent, child);

            child.setWidth(childWidth);
            child.setX(resolveChildX(parent, child));
            child.setY(childY);

            childY += child.getHeight() + spacing;
        }

        childY -= spacing;
        totalHeight = childY - startChildY + (padding * 2);
    }

    private int calculateMaxChildWidth(List<BaseElement> children) {
        int max = 0;
        for (BaseElement child : children) {
            int w = child.getPreferredWidth() > 0
                    ? child.getPreferredWidth()
                    : child.getWidth();
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
            sum += child.getHeight() + spacing;
        }

        return sum - spacing;
    }

    private int resolveChildX(BaseElement parent, BaseElement child) {
        return switch(alignmentX) {
            case START -> parent.getX() + padding;
            case END -> parent.getX() + parent.getWidth() - child.getWidth() - padding;
            default -> parent.getX() + (parent.getWidth() / 2) - (child.getWidth() / 2);

        } + alignmentXOffset;
    }

    private int resolveStartChildY(BaseElement parent, List<BaseElement> children) {
        int childrenHeight = this.calculateChildrenHeight(children);

        return switch(alignmentY) {
            case Alignment.START -> parent.getY() + padding;
            case Alignment.CENTER -> parent.getY() + (parent.getHeight() - childrenHeight) / 2;
            case Alignment.END -> parent.getY() + parent.getHeight() - childrenHeight - padding;

        } + alignmentYOffset;
    }

    private int resolveChildWidth(BaseElement parent, BaseElement child) {
        int maxWidth = parent.getWidth() - (padding * 2);
        int preferredWidth = child.getPreferredWidth();

        return preferredWidth > 0 ? Math.min(preferredWidth, maxWidth) : child.getWidth();
    }

    public Alignment getAlignmentX() {
        return alignmentX;
    }

    public Alignment getAlignmentY() {
        return alignmentY;
    }

    public void setAlignmentX(Alignment alignmentX) {
        this.alignmentX = alignmentX;
    }

    public void setAlignmentY(Alignment alignmentY) {
        this.alignmentY = alignmentY;
    }

    public void setPadding(int padding) {
        this.padding = padding;
    }

    public void setSpacing(int spacing) {
        this.spacing = spacing;
    }

}
