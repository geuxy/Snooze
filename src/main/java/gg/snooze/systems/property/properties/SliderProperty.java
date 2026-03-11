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

@Getter
public final class SliderProperty extends Property<SliderProperty> {

    public static final double MIN_INCREMENT = 0.0001D;

    private final double minimum, maximum, increment;
    private final Set<BiConsumer<Double, Double>> actions = new HashSet<>();
    private double value;

    public SliderProperty(PropertyMetadata metadata, PropertyOwner owner, double minimum, double maximum, double increment) {
        super(metadata, owner);
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = Math.clamp(increment, MIN_INCREMENT, maximum - minimum);
        this.setValue(this.minimum);
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

    public SliderProperty addAction(BiConsumer<Double, Double> consumer) {
        this.actions.add(consumer);
        return this;
    }

    public void setValue(double value) {
        if(value >= this.minimum && value <= this.maximum) {
            double newValue = Math.round(value / this.increment) * this.increment;

            for(BiConsumer<Double, Double> action : this.actions) {
                action.accept(this.value, newValue);
            }

            this.value = newValue;
        }
    }

}
