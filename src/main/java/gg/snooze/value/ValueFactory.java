package gg.snooze.value;

import gg.snooze.Snooze;
import gg.snooze.value.values.*;
import lombok.experimental.UtilityClass;

import java.util.function.Function;

@UtilityClass
public class ValueFactory {

    public <E> EnumBaseValue<E> buildEnum(String name, E[] options, Function<E, String> nameFunc) {
        return new EnumBaseValue<>(
                name,
                Snooze.INSTANCE.modules.getAddingModule(),
                options,
                nameFunc
        );
    }

    public MultiBaseValue buildMulti(String name, String... options) {
        return new MultiBaseValue(
                name,
                Snooze.INSTANCE.modules.getAddingModule(),
                options
        );
    }

    public BoolBaseValue buildBool(String name) {
        return new BoolBaseValue(
                name,
                Snooze.INSTANCE.modules.getAddingModule()
        );
    }

    public DoubleBaseValue buildDouble(String name, double min, double max, double step) {
        return new DoubleBaseValue(
                name,
                Snooze.INSTANCE.modules.getAddingModule(),
                min,
                max,
                step
        );
    }

    public RangeBaseValue buildRange(String name, double min, double max, double step) {
        return new RangeBaseValue(
                name,
                Snooze.INSTANCE.modules.getAddingModule(),
                min,
                max,
                step
        );
    }

}
