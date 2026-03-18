package gg.snooze.value;

import java.util.LinkedHashMap;

public interface ValueOwner {

    LinkedHashMap<String, BaseValue<?>> getProperties();

}
