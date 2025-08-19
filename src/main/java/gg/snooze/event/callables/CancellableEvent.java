package gg.snooze.event.callables;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public abstract class CancellableEvent<T> extends AbstractEvent<T> {

    private boolean cancelled = false;

    public void cancel() {
        this.setCancelled(true);
    }

}
