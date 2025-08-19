package gg.snooze.property;

import com.google.gson.JsonElement;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BooleanSupplier;

@Getter @Setter
public abstract class Property<T extends Property<T>> {

    private final PropertyMetadata metadata;

    private BooleanSupplier visible = () -> true;

    public Property(PropertyMetadata metadata, PropertyOwner owner) {
        this.metadata = metadata;
        if(owner != null) owner.getProperties().put(this.metadata.name(), this);
    }

    public abstract T getSelf();
    public abstract JsonElement toJson();
    public abstract void parseJson(JsonElement element);

}
