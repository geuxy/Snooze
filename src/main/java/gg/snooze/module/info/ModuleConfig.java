package gg.snooze.module.info;

import gg.snooze.value.BaseValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.LinkedHashMap;

@Getter @Setter @AllArgsConstructor
public class ModuleConfig {

    private final LinkedHashMap<String, BaseValue<?>> properties = new LinkedHashMap<>();

    private boolean enabled;
    private int keyCode;

}
