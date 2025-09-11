package gg.snooze.systems.property.properties;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import gg.snooze.systems.property.Property;
import gg.snooze.systems.property.PropertyMetadata;
import gg.snooze.systems.property.PropertyOwner;
import lombok.Getter;

@Getter
public final class RangeProperty extends Property<RangeProperty> {

    public static final double MIN_INCREMENT = 0.0001D;

    private final double minimum, maximum, increment;
    private double minValue, maxValue;

    public RangeProperty(PropertyMetadata metadata, PropertyOwner owner, double value, double endValue, double minimum, double maximum, double increment) {
        super(metadata, owner);
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = Math.clamp(increment, MIN_INCREMENT, maximum - minimum);
        this.setMinValueUnsafe(Math.min(value, endValue));
        this.setMaxValueUnsafe(Math.max(value, endValue));
    };

    @Override
    public RangeProperty getSelf() {
        return this;
    }

    @Override
    public JsonElement toJson() {
        JsonArray valuesJson = new JsonArray();
        valuesJson.add(minValue);
        valuesJson.add(maxValue);
        return valuesJson;
    }

    @Override
    public void parseJson(JsonElement element) {
        if(element instanceof JsonArray a && a.size() > 1) {
            double firstVal = this.minValue;
            double secondVal = this.maxValue;

            JsonElement first = a.get(0), second = a.get(1);

            if(first instanceof JsonPrimitive p && p.isNumber())
                firstVal = p.getAsDouble();

            if(second instanceof JsonPrimitive p && p.isNumber())
                secondVal = p.getAsDouble();

            this.setMinValueUnsafe(Math.min(firstVal, secondVal));
            this.setMaxValueUnsafe(Math.max(firstVal, secondVal));
        }
    }

    public void setMinValue(double minValue) {
        if(minValue <= this.maxValue) {
            this.setMinValueUnsafe(minValue);
        }
    }

    public void setMaxValue(double maxValue) {
        if(maxValue >= this.minValue) {
            this.setMaxValueUnsafe(maxValue);
        }
    }

    private void setMinValueUnsafe(double minValue) {
        if(minValue >= this.minimum && minValue <= this.maximum) {
            this.minValue = Math.round(minValue / this.increment) * this.increment;
        }
    }

    private void setMaxValueUnsafe(double maxValue) {
        if(maxValue >= this.minimum && maxValue <= this.maximum) {
            this.maxValue = Math.round(maxValue / this.increment) * this.increment;
        }
    }

}
