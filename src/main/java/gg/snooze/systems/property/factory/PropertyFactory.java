package gg.snooze.systems.property.factory;

import gg.snooze.core.Snooze;
import gg.snooze.systems.property.PropertyMetadata;
import gg.snooze.systems.property.properties.*;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.function.Function;

@UtilityClass
public class PropertyFactory {

    private static final String EMPTY_DESCRIPTION = "None";

    public <E> ModeProperty<E> mode(String name, E[] options, Function<E, String> nameFunction) {
        return new ModeProperty<>(
                new PropertyMetadata(name, EMPTY_DESCRIPTION),
                Snooze.INSTANCE.modules().getAddingModule(),
                nameFunction,
                0,
                options
        );
    }

    public MultiToggleProperty multi(String name, Map<String, Boolean> options) {
        return new MultiToggleProperty(
                new PropertyMetadata(name, EMPTY_DESCRIPTION),
                Snooze.INSTANCE.modules().getAddingModule(),
                options
        );
    }

    public ToggleProperty toggle(String name) {
        return new ToggleProperty(
                new PropertyMetadata(name, EMPTY_DESCRIPTION),
                Snooze.INSTANCE.modules().getAddingModule()
        );
    }

    public SliderProperty slider(String name, double min, double max, double increment) {
        return new SliderProperty(
                new PropertyMetadata(name, EMPTY_DESCRIPTION),
                Snooze.INSTANCE.modules().getAddingModule(),
                min,
                max,
                increment
        );
    }

    public RangeProperty range(String name, double min, double max, double increment) {
        return new RangeProperty(
                new PropertyMetadata(name, EMPTY_DESCRIPTION),
                Snooze.INSTANCE.modules().getAddingModule(),
                min,
                max,
                increment
        );
    }

}
