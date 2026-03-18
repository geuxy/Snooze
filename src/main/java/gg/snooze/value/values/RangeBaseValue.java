package gg.snooze.value.values;

import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;
import lombok.Getter;

@Getter
public final class RangeBaseValue extends BaseValue<RangeBaseValue> {

    public static final double MIN_INCREMENT = 0.0001D;

    private final double minimum, maximum, step;
    private double minValue, maxValue;

    public RangeBaseValue(String name, ValueOwner owner, double minimum, double maximum, double step) {
        super(name, owner);
        this.minimum = minimum;
        this.maximum = maximum;
        this.minValue = minimum;
        this.maxValue = minimum;
        this.step = Math.clamp(step, MIN_INCREMENT, maximum - minimum);
    };

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
            this.minValue = Math.round(minValue / this.step) * this.step;
        }
    }

    private void setMaxValueUnsafe(double maxValue) {
        if(maxValue >= this.minimum && maxValue <= this.maximum) {
            this.maxValue = Math.round(maxValue / this.step) * this.step;
        }
    }

}
