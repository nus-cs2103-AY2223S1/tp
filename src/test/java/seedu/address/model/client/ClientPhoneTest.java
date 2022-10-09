package seedu.address.model.client;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Phone;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ClientPhoneTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidClientPhone_throwsIllegalArgumentException() {
        String invalidClientPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new ClientPhone(invalidClientPhone));
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
        assertFalse(ClientPhone.isValidClientPhone("911")); // fewer than 8 numbers
        assertFalse(ClientPhone.isValidClientPhone("124293842033123")); // more than 10 numbers

        // valid phone numbers
        assertTrue(ClientPhone.isValidClientPhone("12345678")); //8 numbers
        assertTrue(ClientPhone.isValidClientPhone("123456789")); //9 numbers
        assertTrue(ClientPhone.isValidClientPhone("1234567891")); //10 numbers
    }
}
