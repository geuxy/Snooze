package gg.snooze.systems.property.properties;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import gg.snooze.systems.property.Property;
import gg.snooze.systems.property.PropertyMetadata;
import gg.snooze.systems.property.PropertyOwner;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;
import java.util.function.Function;

@Getter
public final class ModeProperty<E> extends Property<ModeProperty<E>> {

    private final E[] options;

    private E value;
    private int valueIndex;

    private final Function<E, String> nameFunction;

    private final Set<BiConsumer<E, E>> actions = new HashSet<>();

    public ModeProperty(PropertyMetadata metadata, PropertyOwner owner, Function<E, String> nameFunction, int index, E[] options) {
        super(metadata, owner);
        this.nameFunction = nameFunction;
        this.options = options;
        this.setValue(index);
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

    public ModeProperty<E> addAction(BiConsumer<E, E> consumer) {
        this.actions.add(consumer);
        return this;
    }

    public String getSelectedModeName() {
        return this.nameFunction.apply(this.value);
    }

    public String getModeName(E mode) {
        return this.nameFunction.apply(mode);
    }

    public void setValue(int index) {
        if(index > -1 && index < options.length) {
            E newValue = this.options[index];

            for(BiConsumer<E, E> action : this.actions) {
                action.accept(this.value, newValue);
            }

            this.value = newValue;
            this.valueIndex = index;
        }
    }

}
