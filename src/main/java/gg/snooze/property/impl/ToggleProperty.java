package gg.snooze.property.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import gg.snooze.property.Property;
import gg.snooze.property.PropertyMetadata;
import gg.snooze.property.PropertyOwner;
import lombok.Setter;

@Setter
public final class ToggleProperty extends Property<ToggleProperty> {

    private boolean value;

    public ToggleProperty(PropertyMetadata metadata, PropertyOwner owner, boolean value) {
        super(metadata, owner);
        this.value = value;
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

    public boolean getValue() {
        return this.value;
    }

    public void toggle() {
        this.setValue(!this.value);
    }

}
