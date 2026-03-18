package gg.snooze.value.values;

import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class BoolValue extends BaseValue<BoolValue> {

    private final Set<Consumer<Boolean>> actions = new HashSet<>();

    private boolean value;

    public BoolValue(String name, ValueOwner owner) {
        super(name, owner);
    }

    public BoolValue addAction(Consumer<Boolean> consumer) {
        this.actions.add(consumer);
        return this;
    }

    public void toggle() {
        this.setValue(!this.value);
    }

    public boolean getValue() {
        return this.value;
    }

    public void setValue(boolean value) {
        if(this.value != value) {
            this.value = value;
            this.actions.forEach(a -> a.accept(value));
        }
    }

}
