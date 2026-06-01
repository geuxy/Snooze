package gg.snooze.ui.framework.event;

@FunctionalInterface
public interface Listener<T> {

    void onEvent(T event);

}
