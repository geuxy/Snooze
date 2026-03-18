package gg.snooze.value.values;

import gg.snooze.value.BaseValue;
import gg.snooze.value.ValueOwner;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Function;

@Getter
public final class ModeValue<E> extends BaseValue<ModeValue<E>> {

    private final E[] options;
    private final Function<E, String> nameFunction;
    private int valueIndex;

    private final Set<BiConsumer<E, E>> actions = new HashSet<>();

    public ModeValue(String name, ValueOwner owner, E[] options, Function<E, String> nameFunction) {
        super(name, owner);

        if(options.length == 0) {
            throw new IllegalStateException("Cannot create mode with no enum constants present");
        }

        this.options = options;
        this.nameFunction = nameFunction;
        this.valueIndex = 0;
    }

    public ModeValue<E> addAction(BiConsumer<E, E> consumer) {
        this.actions.add(consumer);
        return this;
    }

    public String getSelectedModeName() {
        return this.nameFunction.apply(this.options[this.valueIndex]);
    }

    public String getModeName(E mode) {
        return this.nameFunction.apply(mode);
    }

    public E getValue() {
        return this.options[this.valueIndex];
    }

    public void setValue(int index) {
        if(index > -1 && index < options.length) {
            E newValue = this.options[index];

            this.actions.forEach(a -> a.accept(getValue(), newValue));
            this.valueIndex = index;
        }
    }

}
