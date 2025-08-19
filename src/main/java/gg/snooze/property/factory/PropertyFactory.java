package gg.snooze.property.factory;

import gg.snooze.core.Snooze;
import gg.snooze.property.PropertyMetadata;
import gg.snooze.property.PropertyOwner;
import gg.snooze.property.impl.*;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;

import java.util.Map;
import java.util.function.BooleanSupplier;

@UtilityClass
public class PropertyFactory {

    public static ToggleBuilder toggle() {
        return new ToggleBuilder();
    }

    public static NoteBuilder note() {
        return new NoteBuilder();
    }

    public static MultiToggleBuilder multiToggle() {
        return new MultiToggleBuilder();
    }

    public static <E extends Enum<E>> ModeBuilder<E> mode() {
        return new ModeBuilder<>();
    }

    public static SliderBuilder slider() {
        return new SliderBuilder();
    }

    public static RangeBuilder range() {
        return new RangeBuilder();
    }

    @Setter @Accessors(fluent = true)
    public class ToggleBuilder {

        private PropertyMetadata metadata;
        private BooleanSupplier visible = () -> true;
        private PropertyOwner owner = Snooze.INSTANCE.modules().getAddingModule();
        private boolean value;

        public ToggleProperty build() {
            ToggleProperty property = new ToggleProperty(metadata, owner, value);
            property.setVisible(visible);

            return property;
        }
    }

    @Setter @Accessors(fluent = true)
    public class MultiToggleBuilder {

        private PropertyMetadata metadata;
        private BooleanSupplier visible = () -> true;
        private PropertyOwner owner = Snooze.INSTANCE.modules().getAddingModule();
        private Map<String, Boolean> entries;

        public MultiToggleProperty build() {
            MultiToggleProperty property = new MultiToggleProperty(metadata, owner, entries);
            property.setVisible(visible);

            return property;
        }
    }

    @Setter @Accessors(fluent = true)
    public class ModeBuilder<E extends Enum<E>> {

        private PropertyMetadata metadata;
        private BooleanSupplier visible = () -> true;
        private PropertyOwner owner = Snooze.INSTANCE.modules().getAddingModule();
        private E[] options;
        private int value;

        public ModeProperty<E> build() {
            ModeProperty<E> property = new ModeProperty<>(metadata, owner, value, options);
            property.setVisible(visible);

            return property;
        }
    }

    @Setter @Accessors(fluent = true)
    public class NoteBuilder {

        private PropertyMetadata metadata;
        private BooleanSupplier visible = () -> true;
        private PropertyOwner owner = Snooze.INSTANCE.modules().getAddingModule();

        public NoteProperty build() {
            NoteProperty property = new NoteProperty(metadata, owner);
            property.setVisible(visible);

            return property;
        }
    }

    @Setter @Accessors(fluent = true)
    public class SliderBuilder {

        private PropertyMetadata metadata;
        private BooleanSupplier visible = () -> true;
        private PropertyOwner owner = Snooze.INSTANCE.modules().getAddingModule();
        private double value, minimum, maximum, increment;

        public SliderBuilder bounds(double minimum, double maximum) {
            this.minimum = minimum;
            this.maximum = maximum;
            return this;
        }

        public SliderProperty build() {
            SliderProperty property = new SliderProperty(metadata, owner, value, minimum, maximum, increment);
            property.setVisible(visible);

            return property;
        }
    }

    @Setter @Accessors(fluent = true)
    public class RangeBuilder {

        private PropertyMetadata metadata;
        private BooleanSupplier visible = () -> true;
        private PropertyOwner owner = Snooze.INSTANCE.modules().getAddingModule();
        private double minValue, maxValue, minimum, maximum, increment;

        public RangeBuilder values(double minimum, double maximum) {
            this.minValue = minimum;
            this.maxValue = maximum;
            return this;
        }

        public RangeBuilder bounds(double minimum, double maximum) {
            this.minimum = minimum;
            this.maximum = maximum;
            return this;
        }

        public RangeProperty build() {
            RangeProperty property = new RangeProperty(metadata, owner, minValue, maxValue, minimum, maximum, increment);
            property.setVisible(visible);

            return property;
        }
    }

}
