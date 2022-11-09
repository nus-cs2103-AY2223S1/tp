package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ServerTest {

    public static final Server VALID_TEST_SERVER = new Server("test@123.456");

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Server(null));
    }

    @Test
    public void isValidServerName() {
        // null Server name
        assertThrows(NullPointerException.class, () -> Server.isValidServerName(null));

        // Invalid server names
        assertFalse(Server.isValidServerName("randomMinecraftServer")); // does not include server address
        assertFalse(Server.isValidServerName("111.222.333")); // does not include server name
        assertFalse(Server.isValidServerName("server#111.222.333")); // invalid use of # symbol

        // Valid server names
        assertTrue(Server.isValidServerName("server@111.222.333"));
        assertTrue(Server.isValidServerName("server_name@server_address"));
    }

    @Test
    public void isEqualServer() {
        Server testServer = new Server("test@123.456");
        assertTrue(VALID_TEST_SERVER.equals(testServer));
    }
}
