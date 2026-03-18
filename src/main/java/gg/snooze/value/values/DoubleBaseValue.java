package gg.snooze.value.values;

import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;

@Getter
public final class DoubleBaseValue extends BaseValue<DoubleBaseValue> {

    public static final double MIN_INCREMENT = 0.0001D;

    private final double minimum, maximum, increment;
    private final Set<BiConsumer<Double, Double>> actions = new HashSet<>();
    private double value;

    public DoubleBaseValue(String name, ValueOwner owner, double minimum, double maximum, double step) {
        super(name, owner);
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = Math.clamp(step, MIN_INCREMENT, maximum - minimum);
        this.setValue(this.minimum);
    }

    public DoubleBaseValue addAction(BiConsumer<Double, Double> consumer) {
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
