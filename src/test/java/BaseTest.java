import org.db.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public class BaseTest {
    @BeforeAll
    public static void serverSetup() throws IOException {
        String[] args = new String[0];
        Server.main(args);
    }

    @AfterAll
    public static void turnDown() throws IOException {
        Server.stop();
    }
}
