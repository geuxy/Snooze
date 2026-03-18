package gg.snooze.value.values;

import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;
import lombok.Getter;

import java.util.LinkedHashMap;

public final class MultiBaseValue extends BaseValue<MultiBaseValue> {

    @Getter
    private final LinkedHashMap<String, Boolean> entries;

    public MultiBaseValue(String name, ValueOwner owner, String... options) {
        super(name, owner);
        this.entries = new LinkedHashMap<>();

        for(String option : options) {
            this.entries.put(option, false);
        }
    }

    public boolean getValue(String name) {
        return this.entries.getOrDefault(name, false);
    }

    public void setValue(String name, boolean value) {
        if(this.entries.containsKey(name)) {
            this.entries.put(name, value);
        }
    }

}
