package gg.snooze.property.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import gg.snooze.property.Property;
import gg.snooze.property.PropertyMetadata;
import gg.snooze.property.PropertyOwner;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

@Getter
public final class SliderProperty extends Property<SliderProperty> {

    public static final double MIN_INCREMENT = 0.0001D;

    private final double minimum, maximum, increment;
    private final Set<BiConsumer<Double, Double>> valueChangeListeners = new HashSet<>();
    private double value;

    public SliderProperty(PropertyMetadata metadata, PropertyOwner owner, double value, double minimum, double maximum, double increment) {
        super(metadata, owner);
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = Math.clamp(increment, MIN_INCREMENT, maximum - minimum);
        this.setValue(value);
    };

    @Override
    public SliderProperty getSelf() {
        return this;
    }

    @Override
    public JsonElement toJson() {
        return new JsonPrimitive(value);
    }

    @Override
    public void parseJson(JsonElement element) {
        if(element instanceof JsonPrimitive p && p.isNumber()) {
            this.setValue(p.getAsDouble());
        }
    }

    public void setValue(double value) {
        if(value >= this.minimum && value <= this.maximum) {
            this.value = Math.round(value / this.increment) * this.increment;
        }
    }

}
