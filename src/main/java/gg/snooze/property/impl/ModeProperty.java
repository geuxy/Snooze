package gg.snooze.property.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import gg.snooze.property.Property;
import gg.snooze.property.PropertyMetadata;
import gg.snooze.property.PropertyOwner;
import lombok.Getter;

@Getter
public final class ModeProperty<E extends Enum<E>> extends Property<ModeProperty<E>> {

    private final E[] options;

    private E value;
    private int valueIndex;

    public ModeProperty(PropertyMetadata metadata, PropertyOwner owner, int index, E[] options) {
        super(metadata, owner);
        this.options = options;
        this.value = options[0];
        this.valueIndex = index;
    }

    @Override
    public ModeProperty<E> getSelf() {
        return this;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(valueIndex);
    }

    @Override
    public void parseJson(JsonElement element) {
        if(element instanceof JsonPrimitive p && p.isNumber()) {
            this.setValue(p.getAsInt());
        }
    }

    public void setValue(int index) {
        if(index > -1 && index < options.length) {
            this.value = this.options[index];
            this.valueIndex = index;
        }
    }

}
