package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.Name;

public class ClientTest {

    private static final Client defaultClient = new Client(new Name("default"));

    private static final Client otherClient = new Client(new Name("test"));

    public static Client getDefaultClient() {
        return defaultClient;
    }

    public static Client getOtherClient() {
        return otherClient;
    }

    @Test
    public void constructor_nullParam_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Client(null));
    }

    @Test
    public void constructor_invalidArguments_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Client(new Name(invalidName)));
    }

    @Test
    public void equals() {
        Client defaultClient = getDefaultClient();
        Client otherClient = getOtherClient();

        // same values -> returns true
        assertEquals(defaultClient, defaultClient);

        // same object -> returns true
        assertEquals(defaultClient, defaultClient);

        // different object -> returns false
        assertNotEquals(defaultClient, otherClient);

        // null -> returns false
        assertNotEquals(null, defaultClient);
    }

}
