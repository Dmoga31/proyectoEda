package Test;
import Cache.Exception.KeyNotFoundException;
import Cache.ICache;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

class ICacheTest {

    @Test
    void getAll() throws IOException {
        ICache cache = new ICache();
        cache.addNew("key1", "value1");
        String[] keys = cache.getAll();
        assertEquals(1, keys.length);
    }

    @Test
    void read() throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        assertEquals("value1", cache.read("key1"));
    }

    @Test
    void exists() throws IOException {
        ICache cache = new ICache();
        assertTrue(cache.exists("key2"));
    }

    @Test
    void update() throws IOException, KeyNotFoundException {
        ICache cache = new ICache();
        cache.update("key2", "value3");
        assertEquals("value3", cache.read("key2"));
    }

    @Test
    void addNew() throws IOException {
        ICache cache = new ICache();
        cache.addNew("key4", "value3");
        assertTrue(cache.exists("key4"));
    }

    @Test
    void size() throws IOException {
        ICache cache = new ICache();
        assertEquals(2, cache.size());
    }


    @Test
    void remove() throws IOException {
        ICache cache = new ICache();
        cache.remove("key2");
        assertFalse(cache.exists("key2"));

    }
}

