package gg.snooze.value.values;

import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;
import lombok.Getter;
import org.apache.logging.log4j.util.TriConsumer;

import java.util.HashSet;
import java.util.Set;

@Getter
public final class RangeValue extends BaseValue<RangeValue> {

    public static final double MIN_INCREMENT = 0.0001D;

    private final Set<TriConsumer<Double, Double, Boolean>> actions = new HashSet<>();

    private final double minimum, maximum, step;
    private double minValue, maxValue;

    public RangeValue(String name, ValueOwner owner, double minimum, double maximum, double step) {
        super(name, owner);
        this.minimum = minimum;
        this.maximum = maximum;
        this.minValue = minimum;
        this.maxValue = minimum;
        this.step = Math.clamp(step, MIN_INCREMENT, maximum - minimum);
    }

    public RangeValue addAction(TriConsumer<Double, Double, Boolean> consumer) {
        this.actions.add(consumer);
        return this;
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
            double newMinValue = Math.round(minValue / this.step) * this.step;

            actions.forEach(a -> a.accept(minValue, newMinValue, false));
            this.minValue = newMinValue;
        }
    }

    private void setMaxValueUnsafe(double maxValue) {
        if(maxValue >= this.minimum && maxValue <= this.maximum) {
            double newMaxValue = Math.round(maxValue / this.step) * this.step;
            actions.forEach(a -> a.accept(maxValue, newMaxValue, true));
            this.maxValue = newMaxValue;
        }
    }

}
