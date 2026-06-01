package gg.snooze.manager;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

public abstract class ListManager<T> implements Iterable<T> {

    protected final List<T> data = createList();

    public List<T> createList() {
        return new ArrayList<>();
    }

    @Override
    public @NotNull Iterator<T> iterator() {
        return this.data.iterator();
    }

    @SafeVarargs
    protected final void add(T... elements) {
        this.data.addAll(Arrays.asList(elements));
    }

    @SafeVarargs
    protected final void add(Supplier<T>... suppliers) {
        Stream.of(suppliers).map(Supplier::get).forEach(this.data::add);
    }

    public Collection<T> findAll(Predicate<T> predicate) {
        return this.data.stream().filter(predicate).toList();
    }

    public ArrayList<T> view() {
        return new ArrayList<>(this.data);
    }

}
