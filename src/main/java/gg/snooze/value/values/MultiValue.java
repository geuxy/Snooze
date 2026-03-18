package gg.snooze.value.values;

import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;

import lombok.Getter;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.function.BiConsumer;

public final class MultiValue extends BaseValue<MultiValue> {

    private final Set<BiConsumer<String, Boolean>> actions = new HashSet<>();

    @Getter
    private final LinkedHashMap<String, Boolean> entries;

    public MultiValue(String name, ValueOwner owner, String... options) {
        super(name, owner);
        this.entries = new LinkedHashMap<>();

        for(String option : options) {
            this.entries.put(option, false);
        }
    }

    public MultiValue addAction(BiConsumer<String, Boolean> consumer) {
        this.actions.add(consumer);
        return this;
    }

    public boolean getValue(String name) {
        return this.entries.getOrDefault(name, false);
    }

    public void setValue(String name, boolean value) {
        Boolean pluh = this.entries.get(name);

        if(pluh != null && pluh != value) {
            this.entries.put(name, value);
            this.actions.forEach(a -> a.accept(name, value));
        }
    }

}
