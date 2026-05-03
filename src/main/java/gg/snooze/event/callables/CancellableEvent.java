package gg.snooze.event.callables;

import gg.snooze.event.Cancellable;
import lombok.Getter;

public abstract class CancellableEvent extends BaseEvent implements Cancellable {

    @Getter
    private boolean cancelled = false;

    @Override
    public void cancel() {
        this.cancelled = true;
    }

}
