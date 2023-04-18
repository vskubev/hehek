package org.db.storage;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStorage<K, V> implements Storage<K, V> {

    private final Map<K, V> inMemory;

    public InMemoryStorage() {
        inMemory = new ConcurrentHashMap<>();
    }

    @Override
    public V find(K key) {
        return inMemory.get(key);
    }

    @Override
    public V save(K key, V value) {
        inMemory.put(key, value);
        return value;
    }

    @Override
    public void delete(K key) {
        inMemory.remove(key);
    }
}
