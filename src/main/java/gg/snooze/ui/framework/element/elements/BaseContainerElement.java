package gg.snooze.ui.framework.element.elements;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.event.BaseEvent;
import gg.snooze.ui.framework.event.EventInvoker;
import gg.snooze.ui.framework.layout.Layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseContainerElement extends BaseElement {

    private final List<BaseElement> children = new ArrayList<>();

    private Layout layout;

    @Override
    public void invoke(int eventId, BaseEvent event, EventInvoker eventInvoker) {
        if(!event.canInvoke(this)) {
            return;
        }

        super.invoke(eventId, event, eventInvoker);

        if(event.isConsumed()) {
            return;
        }

        for(int i = this.children.size() - 1; i >= 0; i--) {
            BaseElement child = this.children.get(i);

            if(!event.canInvoke(child)) {
                continue;
            }

            child.invoke(eventId, event, eventInvoker);

            if(event.isConsumed()) {
                break;
            }
        }
    }

    public void addChild(BaseElement element) {
        this.children.add(element);
        this.updateLayout();
    }

    public void addChildren(BaseElement... elements) {
        this.children.addAll(Arrays.asList(elements));
        this.updateLayout();
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
        this.updateLayout();
    }

    public void updateLayout() {
        if(layout != null) {
            this.layout.updateElements(this, this.children);
        }
    }

    public boolean isResizable() {
        return true;
    }

    public List<BaseElement> getChildren() {
        return children;
    }

}
