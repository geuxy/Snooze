package gg.snooze.systems.module.info;

import gg.snooze.systems.property.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter @Setter @AllArgsConstructor
public class ModuleConfig {

    private final LinkedHashMap<String, Property<?>> properties = new LinkedHashMap<>();

    private boolean enabled;
    private int keyCode;

}
