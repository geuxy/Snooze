package gg.snooze.property.impl;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import gg.snooze.property.Property;
import gg.snooze.property.PropertyMetadata;
import gg.snooze.property.PropertyOwner;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.Map;

public final class MultiToggleProperty extends Property<MultiToggleProperty> {

    @Getter
    private final LinkedHashMap<String, Boolean> entries;

    public MultiToggleProperty(PropertyMetadata metadata, PropertyOwner owner, Map<String, Boolean> entries) {
        super(metadata, owner);
        this.entries = new LinkedHashMap<>(entries);
    }

    @Override
    public MultiToggleProperty getSelf() {
        return this;
    }

    @Override
    public JsonElement toJson() {
        JsonObject entriesJson = new JsonObject();

        this.entries.forEach(entriesJson::addProperty);

        return entriesJson;
    }

    @Override
    public void parseJson(JsonElement element) {
        if(element instanceof JsonObject j && !j.isEmpty()) {
            this.entries.forEach((k, v) -> {
                if(j.has(k)) {
                    JsonElement valueJson = j.get(k);

                    if(valueJson instanceof JsonPrimitive p && p.isBoolean()) {
                        this.entries.put(k, p.getAsBoolean());
                    }
                }
            });
        }
    }

    public boolean getValue(String name) {
        return this.entries.getOrDefault(name, false);
    }

    public void setValue(String name, boolean value) {
        if(this.entries.containsKey(name)) {
            this.entries.put(name, value);
        }
    }

}
