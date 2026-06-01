package gg.snooze.ui.framework.layout.impl;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.element.elements.BaseContainerElement;
import gg.snooze.ui.framework.layout.Layout;

import java.util.List;

public class GridLayout implements Layout {

    public final int columns, rows;
    public int parentPadding, childPadding;

    public GridLayout(int columns, int rows) {
        this.columns = Math.max(1, columns);
        this.rows = Math.max(1, rows);
    }

    @Override
    public void updateElements(BaseContainerElement parent, List<BaseElement> children) {
        int childWidth = parent.width / columns;
        int childHeight = parent.height / rows;

        int column = 0;
        int row = 0;
        for(BaseElement child : children) {
            int childX = parent.x + parentPadding + (childWidth * column);
            int childY = parent.y + parentPadding + (childHeight * row);

            child.x = childX;
            child.y = childY;
            child.width = childWidth - (parentPadding * 2);
            child.height = childHeight - (parentPadding * 2);

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
