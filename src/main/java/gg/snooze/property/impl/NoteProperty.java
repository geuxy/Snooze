package gg.snooze.property.impl;

import com.google.gson.JsonElement;
import gg.snooze.property.Property;
import gg.snooze.property.PropertyMetadata;
import gg.snooze.property.PropertyOwner;

public final class NoteProperty extends Property<NoteProperty> {

    public NoteProperty(PropertyMetadata metadata, PropertyOwner owner) {
        super(metadata, owner);
    }

    @Override
    public NoteProperty getSelf() {
        return this;
    }

    @Override
    public JsonElement toJson() {
        return null;
    }

    @Override
    public void parseJson(JsonElement element) {
    }

}
