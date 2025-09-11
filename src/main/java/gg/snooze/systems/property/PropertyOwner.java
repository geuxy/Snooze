package gg.snooze.systems.property;

import java.util.LinkedHashMap;

public interface PropertyOwner {

    LinkedHashMap<String, Property<?>> getProperties();

}
