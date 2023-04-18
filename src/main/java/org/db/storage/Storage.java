package org.db.storage;

public interface Storage<K, V> {
    V find(K key);

    V save(K key, V value);

    void delete(K key);
}
