import org.db.Client;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {

    private Client client;

    @BeforeEach
    public void setup() throws IOException {
        client = new Client();
        client.startConnection("127.0.0.1", 4444);
    }

    @Test
    public void testKeyValueStorage() throws IOException {
        String msg1 = client.sendMessage("put key value");
        String msg2 = client.sendMessage("get key");

        assertEquals("value", msg1);
        assertEquals("value", msg2);
    }

    @AfterEach
    public void tearDown() throws IOException {
        client.stopConnection();
    }
}
