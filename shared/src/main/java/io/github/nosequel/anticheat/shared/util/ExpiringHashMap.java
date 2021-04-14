package io.github.nosequel.anticheat.shared.util;

import java.util.HashMap;
import java.util.Map;

public class ExpiringHashMap<K, V> extends HashMap<K, V> {

    private final Map<Object, Long> times = new HashMap<>();

    @Override
    public V get(Object object) {
        if (!this.times.containsKey(object) || System.currentTimeMillis() > this.times.get(object) + 15000) {
            super.remove(object);
            this.times.remove(object);
        }

        return super.get(object);
    }

    @Override
    public V put(K k, V v) {
        if(!this.times.containsKey(k)) {
            this.times.put(k, System.currentTimeMillis());
        }

        return super.put(k, v);
    }
}