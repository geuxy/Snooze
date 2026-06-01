package gg.snooze.ui.framework.event;

@FunctionalInterface
public interface Listener {

    void onEvent(BaseEvent event);

}
