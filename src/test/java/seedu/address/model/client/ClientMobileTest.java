package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClientMobileTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientMobile(null));
    }

    @Test
    public void constructor_invalidClientMobile_throwsIllegalArgumentException() {
        String invalidClientMobile = "";
        assertThrows(IllegalArgumentException.class, () -> new ClientMobile(invalidClientMobile));
    }

    @Test
    public void isValidMobile() {
        // null mobile number
        assertThrows(NullPointerException.class, () -> ClientMobile.isValidClientMobile(null));

        // invalid mobile numbers
        assertFalse(ClientMobile.isValidClientMobile("")); // empty string
        assertFalse(ClientMobile.isValidClientMobile(" ")); // spaces only
        assertFalse(ClientMobile.isValidClientMobile("91")); // less than 3 numbers
        assertFalse(ClientMobile.isValidClientMobile("phone")); // non-numeric
        assertFalse(ClientMobile.isValidClientMobile("9011p041")); // alphabets within digits
        assertFalse(ClientMobile.isValidClientMobile("9312 1534")); // spaces within digits

        // valid mobile numbers
        assertTrue(ClientMobile.isValidClientMobile("12345678")); //8 numbers
        assertTrue(ClientMobile.isValidClientMobile("123456789")); //9 numbers
        assertTrue(ClientMobile.isValidClientMobile("1234567891")); //10 numbers
        assertTrue(ClientMobile.isValidClientMobile("911")); // fewer than 8 numbers
        assertTrue(ClientMobile.isValidClientMobile("124293842033123")); // more than 10 numbers
    }
}
