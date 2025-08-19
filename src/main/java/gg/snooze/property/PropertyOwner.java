package gg.snooze.property;

import java.util.LinkedHashMap;
import java.util.Optional;

public interface PropertyOwner {

    LinkedHashMap<String, Property<?>> getProperties();

}
