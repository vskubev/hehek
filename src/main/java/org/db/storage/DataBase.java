package org.db.storage;

public class DataBase implements Storage<String, String> {
    private final Storage<String, String> inMemory = new InMemoryStorage<>();

    @Override
    public String find(String key) {
        return inMemory.find(key);
    }

    @Override
    public String save(String key, String value) {
        return inMemory.save(key, value);
    }

    @Override
    public void delete(String key) {
        inMemory.delete(key);
    }
}
