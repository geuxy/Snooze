package gg.snooze.event;

@FunctionalInterface
public interface Cancellable {

    void cancel();
    default boolean isCancelled() {
        return false;
    }

}
