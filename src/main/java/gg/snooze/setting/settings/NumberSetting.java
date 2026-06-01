package gg.snooze.setting.settings;

import gg.snooze.setting.Setting;
import gg.snooze.setting.SettingOwner;

public final class NumberSetting extends Setting {

    public static final double MIN_INCREMENT = 0.0001D;

    private final double minimum, maximum, increment;
    private double value;

    public NumberSetting(String name, SettingOwner owner, double minimum, double maximum, double step) {
        super(name, owner);
        this.minimum = minimum;
        this.maximum = maximum;
        this.increment = Math.clamp(step, MIN_INCREMENT, maximum - minimum);
        this.setValue(this.minimum);
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        if(value >= this.minimum && value <= this.maximum) {
            this.value = Math.round(value / this.increment) * this.increment;
        }
    }

    public double getMinimum() {
        return minimum;
    }

    public double getMaximum() {
        return maximum;
    }

}
