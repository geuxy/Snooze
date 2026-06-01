package gg.snooze.setting.settings.option;

public class Option<V> {

    private final String key;
    private V value;

    public Option(String key, V value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
