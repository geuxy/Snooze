package gg.snooze.value;

import lombok.Getter;
import lombok.Setter;

import java.util.function.BooleanSupplier;

@Getter @Setter
public abstract class BaseValue<T extends BaseValue<T>> {

    private static final BooleanSupplier DEFAULT_VISIBILITY = () -> true;

    private final String name;

    private BooleanSupplier visible;

    public BaseValue(String name, ValueOwner owner) {
        this.name = name;
        this.visible = DEFAULT_VISIBILITY;

        if(owner != null) {
            owner.getProperties().put(this.name, this);
        }
    }

    public T setVisible(BooleanSupplier supplier) {
        this.visible = supplier;
        return (T) this;
    }

}
