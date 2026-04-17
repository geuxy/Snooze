package gg.snooze.manager;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Predicate;

public abstract class MapManager<K, V> implements Iterable<V> {

    private final LinkedHashMap<K, V> data = new LinkedHashMap<>();

    @Override
    public @NotNull Iterator<V> iterator() {
        return this.data.values().iterator();
    }

    public List<V> findAll(Predicate<V> predicate) {
        return this.data.values().stream().filter(predicate).toList();
    }

    public void put(K k, V v) {
        this.data.put(k, v);
    }

}
