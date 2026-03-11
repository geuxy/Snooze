package gg.snooze.systems.property.properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import gg.snooze.systems.property.Property;
import gg.snooze.systems.property.PropertyMetadata;
import gg.snooze.systems.property.PropertyOwner;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public final class ToggleProperty extends Property<ToggleProperty> {

    private final Set<Consumer<Boolean>> actions = new HashSet<>();

    private boolean value;

    public ToggleProperty(PropertyMetadata metadata, PropertyOwner owner) {
        super(metadata, owner);
    }

    @Override
    public ToggleProperty getSelf() {
        return this;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(value);
    }

    @Override
    public void parseJson(JsonElement element) {
        if(element instanceof JsonPrimitive p && p.isBoolean()) {
            this.setValue(p.getAsBoolean());
        }
    }

    public ToggleProperty addAction(Consumer<Boolean> consumer) {
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
