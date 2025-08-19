package gg.snooze.event.callables;

public abstract class AbstractEvent<T> {

    public abstract void call(T listener);

}
