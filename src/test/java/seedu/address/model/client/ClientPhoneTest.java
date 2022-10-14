package seedu.address.model.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class ClientPhoneTest {

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
        assertThrows(NullPointerException.class, () -> ClientPhone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(ClientPhone.isValidPhone("")); // empty string
        assertFalse(ClientPhone.isValidPhone(" ")); // spaces only
        assertFalse(ClientPhone.isValidPhone("91")); // less than 3 numbers
        assertFalse(ClientPhone.isValidPhone("phone")); // non-numeric
        assertFalse(ClientPhone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(ClientPhone.isValidPhone("9312 1534")); // spaces within digits

        // valid phone numbers
        assertTrue(ClientPhone.isValidPhone("911")); // exactly 3 numbers
        assertTrue(ClientPhone.isValidPhone("93121534"));
        assertTrue(ClientPhone.isValidPhone("124293842033123")); // long phone numbers
    }
    @Test
    public void toString_phone_returnsValueInPhone() {
        String value = "93121534";
        ClientPhone phone = new ClientPhone(value);
        assertEquals(phone.toString(), value);
    }
}
