package gg.snooze.systems.module.handler.click;

public abstract class ClickMode {

    public enum ClickType {
        ATTACK,
        USE
    }

    public abstract void click(ClickType type);

}
