package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class MobileTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClientMobile(null));
    }

    @Test
    public void constructor_invalidMobile_throwsIllegalArgumentException() {
        String invalidMobile = "";
        assertThrows(IllegalArgumentException.class, () -> new ClientMobile(invalidMobile));
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
        assertTrue(ClientMobile.isValidClientMobile("911")); // exactly 3 numbers
        assertTrue(ClientMobile.isValidClientMobile("93121534"));
        assertTrue(ClientMobile.isValidClientMobile("124293842033123")); // long mobile numbers
    }
}
