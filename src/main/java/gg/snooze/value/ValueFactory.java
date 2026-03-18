package gg.snooze.value;

import gg.snooze.Snooze;
import gg.snooze.module.Module;
import gg.snooze.value.values.*;

import java.util.function.Function;

public class ValueFactory {

    private static Module module() {
        return Snooze.INSTANCE.modules.getAddingModule();
    }

    public static <E> ModeValue<E> mode(String name, E[] options, Function<E, String> nameFunc) {
        return mode(name, module(), options, nameFunc);
    }

    public static MultiValue multi(String name, String... options) {
        return multi(name, module(), options);
    }

    public static BoolValue bool(String name) {
        return bool(name, module());
    }

    public static NumberValue number(String name, double min, double max, double step) {
        return number(name, module(), min, max, step);
    }

    public static RangeValue range(String name, double min, double max, double step) {
        return range(name, module(), min, max, step);
    }

    public static LabelValue label(String name) {
        return label(name, module());
    }

    public static <E> ModeValue<E> mode(String name, ValueOwner owner, E[] options, Function<E, String> nameFunc) {
        return new ModeValue<>(name, owner, options, nameFunc);
    }

    public static MultiValue multi(String name, ValueOwner owner, String... options) {
        return new MultiValue(name, owner, options);
    }

    public static BoolValue bool(String name, ValueOwner owner) {
        return new BoolValue(name, owner);
    }

    public static NumberValue number(String name, ValueOwner owner, double min, double max, double step) {
        return new NumberValue(name, owner, min, max, step);
    }

    public static RangeValue range(String name, ValueOwner owner, double min, double max, double step) {
        return new RangeValue(name, owner, min, max, step);
    }

    public static LabelValue label(String name, ValueOwner owner) {
        return new LabelValue(name, owner);
    }

}
