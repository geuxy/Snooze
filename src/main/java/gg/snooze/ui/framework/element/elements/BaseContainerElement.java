package gg.snooze.ui.framework.element.elements;

import gg.snooze.ui.framework.element.BaseElement;
import gg.snooze.ui.framework.event.BaseEvent;
import gg.snooze.ui.framework.event.EventInvoker;
import gg.snooze.ui.framework.layout.Layout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class BaseContainerElement<T extends BaseContainerElement<T>> extends BaseElement {

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

    public T addChild(BaseElement element) {
        this.children.add(element);
        this.updateLayout();
        return (T) this;
    }

    public T addChildren(BaseElement... elements) {
        this.children.addAll(Arrays.asList(elements));
        this.updateLayout();
        return (T) this;
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
