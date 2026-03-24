package gg.snooze.event.callables;

import gg.snooze.event.Cancellable;

public abstract class CancellableEvent extends BaseEvent implements Cancellable {

    private boolean cancelled = false;

    @Override
    public void cancel() {
        this.cancelled = true;
    }

}
