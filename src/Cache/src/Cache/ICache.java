package Cache;

import Cache.Exception.DuplicatedKeyException;
import Cache.Exception.KeyNotFoundException;
import Structure.TreeMap;
import java.io.IOException;

public class ICache implements ICacheInterface {
    private String dirname = "cache";
    private TreeMap<String, String> cache;
    private Creator creator = new Creator();

    /**
     * Constructor of the class ICache
     * @throws IOException if the cache directory does not exist.
     */
    public ICache() throws IOException {
        if(!creator.existFile(this.dirname)){
            creator.createFolder(this.dirname);
            cache = new TreeMap<>();
        }else{
            this.cache = new TreeMap<>();
            String[] keys = creator.readFolder(this.dirname);
            for(String key : keys){
                String value = creator.readFile(getFileName(key));
                this.cache.put(key, value);
            }

        }

    }

    /**
     * Returns all the keys of the cache
     * @return String[] with all the keys
     */
    public String[] getAll() {
        if (cache.isEmpty()) {
            return null;
        }
        Object[] keys = cache.keys();
        String[] output = new String[keys.length];
        for (int i = 0; i < keys.length; i++) {
            output[i] = (String) keys[i];
        }

        return output;

    }

    /**
     * Returns the value of the key
     * @param key Key to search
     * @return String with the value
     * @throws KeyNotFoundException if the key is not in the cache
     */
    public String read(String key) throws KeyNotFoundException {
        if (!cache.contains(key)) {
            throw new KeyNotFoundException("Key not found");
        }
        return cache.get(key);
    }

    /**
     * Returns the file name of the key in the cache
     * @param key Key to search
     * @return boolean
     */
    public boolean exists(String key) {
        boolean cacheContains = cache.contains(key);
        boolean fileExists = creator.existFile(getFileName(key));

        if (cacheContains && fileExists) {
            System.out.println(key + " exists in cache");
            return true;
        } else {
            System.out.println(key + " does not exist in cache");
            return false;
        }
    }

    /**
     * Updates the value of the key in the cache
     *
     * @param key   Key to update
     * @param value New value
     */
    public void update(String key, String value) throws IOException {
        cache.put(key, value);
        String fileName = getFileName(key);
        if(creator.existFile(fileName)){
            creator.writeInFile(fileName, value);
        }
    }

    /**
     * Adds a new key to the cache with the value
     * @param key Key to add
     * @param value Value of the key
     * @throws DuplicatedKeyException if the key is already in the cache
     */
    public void addNew(String key, String value) throws IOException {
        if (cache.contains(key)) {
            throw new DuplicatedKeyException("Key already exists");
        }
        String fileName = getFileName(key);
        cache.put(key, value);
        if(creator.createFile(fileName)){
            creator.writeInFile(fileName, value);
        }
    }

    /**
     * Removes the key from the cache
     * @param key key to be removed
     */
    public boolean remove(String key){
        if (cache.contains(key) && creator.existFile(getFileName(key))) {
            creator.deleteFile(getFileName(key));
            return true;
        } else {
            System.out.println(key + " does not exist");
            return false;
        }
    }

    /**
     * Shows the size of the cache
     * @return String
     */
    public int size() {
        return cache.size();
    }

    /**
     * Returns the file name of the key in the cache
     * @param file Key to search
     * @return String
     */
    private String getFileName(String file) {
        return this.dirname + "/" + file + ".txt";
    }

}