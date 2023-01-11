package Cache;

import Cache.Exception.KeyNotFoundException;

import java.io.IOException;

public interface ICacheInterface {
    /**
     * Get all keys stored in cache.
     * @return array of stored keys
     */
    String[] getAll();

    /**
     * Get the value associated with the key passed as argument.
     * @param key Key to look for
     * @return The value associated with the key
     * @throws KeyNotFoundException if key does not exist.
     */
    String read(String key) throws KeyNotFoundException;

    /**
     * Check is a key exists in cache.
     * @param key Key to look for
     * @return True if key exists.
     */
    boolean exists(String key);

    /**
     * Add or update the value associated to a key.
     *
     * @param key   Key to be stored.
     * @param value Value to be stored.
     * @throws IOException the key already exists.
     */
    void update(String key, String value) throws IOException;
    /**
     * Add a value to a new key. If key already exists, it throws an exception.
     * @param key Key to be stored.
     * @param value Value to be stored.
     * @throws IOException the key already exists.
     */
    void addNew(String key, String value) throws IOException;
    /**
     * Remove a key and its value.
     * @param key Key to be stored.
     */
    boolean remove(String key);
    /**
     * Count the keys (and values) stored in cache.
     * @return Count of keys.
     */
    int size();
}