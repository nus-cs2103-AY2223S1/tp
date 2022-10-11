package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientPhone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new ClientPhone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> ClientPhone.isValidClientPhone(null));

        // invalid phone numbers
        assertFalse(ClientPhone.isValidClientPhone("")); // empty string
        assertFalse(ClientPhone.isValidClientPhone(" ")); // spaces only
        assertFalse(ClientPhone.isValidClientPhone("91")); // less than 3 numbers
        assertFalse(ClientPhone.isValidClientPhone("phone")); // non-numeric
        assertFalse(ClientPhone.isValidClientPhone("9011p041")); // alphabets within digits
        assertFalse(ClientPhone.isValidClientPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(ClientPhone.isValidClientPhone("911")); // exactly 3 numbers
        assertTrue(ClientPhone.isValidClientPhone("93121534"));
        assertTrue(ClientPhone.isValidClientPhone("124293842033123")); // long phone numbers
    }
}
