package gg.snooze.value;

import lombok.Getter;

import java.util.function.BooleanSupplier;

@Getter
public abstract class BaseValue<T extends BaseValue<T>> {

    private static final BooleanSupplier DEFAULT_VISIBILITY = () -> true;

    private final String name;

    private BooleanSupplier visibility;

    public BaseValue(String name, ValueOwner owner) {
        this.name = name;
        this.visibility = DEFAULT_VISIBILITY;

        if(owner != null) {
            owner.getProperties().put(this.name, this);
        }
    }

    @SuppressWarnings("unchecked")
    public T showIf(BooleanSupplier supplier) {
        this.visibility = supplier;
        return (T) this;
    }

}
