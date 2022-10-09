package seedu.address.model.client;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ClientNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientName(null));
    }

    @Test
    public void constructor_invalidClientName_throwsIllegalArgumentException() {
        String invalidClientName = "";
        assertThrows(IllegalArgumentException.class, () -> new ClientName(invalidClientName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ClientName.isValidClientName(null));

        // invalid name
        assertFalse(ClientName.isValidClientName("")); // empty string
        assertFalse(ClientName.isValidClientName(" ")); // spaces only
        assertFalse(ClientName.isValidClientName("^")); // only non-alphanumeric characters
        assertFalse(ClientName.isValidClientName("peter*")); // contains non-alphanumeric characters
        assertFalse(ClientName.isValidClientName("David Roger Jackson Ray Jr 2nd")); // long names

        // valid name
        assertTrue(ClientName.isValidClientName("peter jack")); // alphabets only
        assertTrue(ClientName.isValidClientName("peter the 2nd")); // alphanumeric characters
        assertTrue(ClientName.isValidClientName("Capital Tan Alan")); // with capital letters
        assertTrue(ClientName.isValidClientName("12345")); // numbers only
    }
}
