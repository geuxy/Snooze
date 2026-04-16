package gg.snooze.value;

import lombok.Getter;

import java.util.function.BooleanSupplier;

@Getter
public abstract class BaseValue<T extends BaseValue<T>> {

    private static final BooleanSupplier DEFAULT_VISIBILITY = () -> true;
    private static final BaseValue<?> DEFAULT_PARENT = null;

    private final String name;

    private BooleanSupplier visibility;

    private BaseValue<?> parent;

    public BaseValue(String name, ValueOwner owner) {
        this.name = name;
        this.visibility = DEFAULT_VISIBILITY;
        this.parent = DEFAULT_PARENT;

        if(owner != null) {
            owner.getValues().put(this.name, this);
        }
    }

    @SuppressWarnings("unchecked")
    public T showIf(BooleanSupplier supplier) {
        this.visibility = supplier;
        return (T) this;
    }

    @SuppressWarnings("unchecked")
    public T childOf(BaseValue<?> parentValue) {
        this.parent = parentValue;
        return (T) this;
    }

}
