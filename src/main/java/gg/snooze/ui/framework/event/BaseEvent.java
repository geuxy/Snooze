package gg.snooze.ui.framework.event;

import gg.snooze.ui.framework.element.BaseElement;

public abstract class BaseEvent {

    private boolean consumed;

    public final void consume() {
        this.consumed = true;
    }

    public boolean canInvoke(BaseElement element) {
        return true;
    }

    public boolean isConsumed() {
        return consumed;
    }

}
