package App;
import Cache.ICache;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Parameters;
import java.io.IOException;
import java.util.concurrent.Callable;
import Cache.Exception.KeyNotFoundException;
import Cache.Exception.DuplicatedKeyException;


@Command (
        name = "java -jar eda_cache.jar",
        version = "CacheController 1.0",
        mixinStandardHelpOptions = true,
        descriptionHeading = "%n@|bold,red,underline Description|@:%n",
        optionListHeading = "%n@|bold,yellow,underline Options|@:%n",
        commandListHeading = "%n@|bold,green,underline Commands|@:%n",
        description = "Cache Controller is a simple database that stores key-value pairs in memory."
)


public class CacheDB implements Callable<Integer> {

    @Command(name="getAll", description = "Get all keys")
    public Integer getAll() throws Exception {
        ICache cache = new ICache();
        try {
            String[] keys = cache.getAll();
            for (String key : keys) {
                System.out.println(key);
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name="readAll", description = "Reads all the keys in the cache")
    public Integer readAll() throws Exception {
        ICache cache = new ICache();
        try {
            String[] keys = cache.getAll();
            for (String key : keys) {
                System.out.println(key + ": " + cache.read(key));
            }
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        } catch (KeyNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    @Command(name="read", description = "Reads the value associated with the key passed as argument.")
    public Integer read(@Parameters(arity = "1", paramLabel = "Key",description = "Key that will be read") String key) throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        for (String k : cache.getAll()) {
            if (k.equals(key)) {
                System.out.println(cache.read(key));
            }
        }
        return 0;
    }



    @Command(name = "add", description = "Create a new key-\"value\" pair.")
    public Integer add(
            @Parameters(arity = "1", paramLabel = "Key", description = "Key that will store in the cache") String key,
            @Parameters(arity = "1", paramLabel = "\"Value\"" , description = "Value that will be in the key. Value has to be with apostrophes.") String value) throws IOException, DuplicatedKeyException {
        ICache cache = new ICache();
        try {
            cache.addNew(key, value);
            System.out.println(value + " added into " + key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }



    @Command(name = "update", description = "Updates the value associated to a key. Value must have apostrophes.")
    public Integer update(
            @Parameters(arity = "1", paramLabel = "Key", description = "Key that will be changed") String key,
            @Parameters(arity = "1", paramLabel = "New Value", description = "new Value in the Key") String value) throws IOException {
        ICache cache = new ICache();
        try {
            cache.update(key, value);
            System.out.println(key + " updated with " + value);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "remove", description = "Delete a key")
    public Integer remove(@Parameters(arity = "1", paramLabel = "Key", description = "Key that will be deleted") String key) throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        try {
            cache.remove(key);
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Command(name = "size", description = "Gets the size of the cache")
    public Integer size() throws IOException {
        ICache cache = new ICache();
        try {
            System.out.println("There are "+ cache.size() + " keys in the cache");
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @Command(name="exists", description = "Check if a key exist")
    public Integer exists(@Parameters(arity = "1", paramLabel = "Key",description = "Key to use in command") String key) throws IOException {
        ICache cache = new ICache();
        try {
            System.out.println(cache.exists(key));
            return 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }

    }

    @Override
    public Integer call() throws Exception {
        System.out.println("Welcome to Cache Controller!!");
        System.out.println("Here you can create, update, remove, read keys and get values from a cache.");
        System.out.println("");
        System.out.println("Commands: getAll, get, add, put, remove, size, exists");
        System.out.println("Type 'help' to see the usage of each command.");
        return 0;
    }


    public static void main(String[] args) {
        int exitCode = new CommandLine(new CacheDB()).execute(args);
        System.exit(exitCode);
    }

}
