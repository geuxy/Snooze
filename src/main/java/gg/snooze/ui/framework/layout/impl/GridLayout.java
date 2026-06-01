package gg.snooze.ui.framework.layout.impl;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.layout.Layout;

import java.util.List;

public class GridLayout implements Layout {

    private final int columns, rows;

    private int parentPadding, childPadding;

    public GridLayout(int columns, int rows) {
        this.columns = Math.max(1, columns);
        this.rows = Math.max(1, rows);
    }

    @Override
    public void updateElements(BaseContainerElement<?> parent, List<BaseElement> children) {
        int childWidth = parent.getWidth() / columns;
        int childHeight = parent.getHeight() / rows;

        int column = 0;
        int row = 0;
        for(BaseElement child : children) {
            int childX = parent.getX() + parentPadding + (childWidth * column);
            int childY = parent.getY() + parentPadding + (childHeight * row);

            child.setX(childX);
            child.setY(childY);
            child.setWidth(childWidth - (parentPadding * 2));
            child.setHeight(childHeight - (parentPadding * 2));

            column++;

            if(column >= columns) {
                column = 0;
                row++;
            }

            if(row >= rows) {
                break;
            }
        }
    }

}
