package org.db;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryStorage {

    private final Map<String,String> inMemory;

    public InMemoryStorage() {
        inMemory = new ConcurrentHashMap<>();
    }

    public String find(String key) {
        return inMemory.get(key);
    }

    public String save(String key, String value) {
        inMemory.put(key, value);
        return value;
    }

    public void delete(String key) {
        inMemory.remove(key);
    }
}
