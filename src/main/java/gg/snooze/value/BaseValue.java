package gg.snooze.value;

import lombok.Getter;
import lombok.Setter;

import java.util.function.BooleanSupplier;

@Getter @Setter
public abstract class BaseValue<T extends BaseValue<T>> {

    private final String name;

    private BooleanSupplier visible = () -> true;

    public BaseValue(String name, ValueOwner owner) {
        this.name = name;

        if(owner != null) {
            owner.getProperties().put(this.name, this);
        }
    }

    public T setVisible(BooleanSupplier supplier) {
        this.visible = supplier;
        return (T) this;
    }

}
