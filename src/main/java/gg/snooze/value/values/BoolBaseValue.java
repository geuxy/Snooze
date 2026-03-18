package gg.snooze.value.values;

import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class BoolBaseValue extends BaseValue<BoolBaseValue> {

    private final Set<Consumer<Boolean>> actions = new HashSet<>();

    private boolean value;

    public BoolBaseValue(String name, ValueOwner owner) {
        super(name, owner);
    }

    public BoolBaseValue addAction(Consumer<Boolean> consumer) {
        this.actions.add(consumer);
        return this;
    }

    public boolean getValue() {
        return this.value;
    }

    public void toggle() {
        this.setValue(!this.value);
    }

    public void setValue(boolean value) {
        if(this.value != value) {
            this.value = value;

            for(Consumer<Boolean> action : this.actions) {
                action.accept(this.value);
            }
        }
    }

}
