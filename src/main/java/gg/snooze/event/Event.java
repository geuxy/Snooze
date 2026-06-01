package gg.snooze.event;

public interface Event<T> {

    void invoke(T listener);

}
