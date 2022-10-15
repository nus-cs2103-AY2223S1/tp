package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NokPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidNokPhone_throwsIllegalArgumentException() {
        String invalidNokPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidNokPhone));
    }

    @Test
    public void isValidNokPhone() {
        // null next of kin phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidNokPhone(null));

        // invalid next of kin phone numbers
        assertFalse(NokPhone.isValidNokPhone("")); // empty string
        assertFalse(NokPhone.isValidNokPhone(" ")); // spaces only
        assertFalse(NokPhone.isValidNokPhone("91")); // less than 3 numbers
        assertFalse(NokPhone.isValidNokPhone(" phone")); // non-numeric
        assertFalse(NokPhone.isValidNokPhone("9011s041")); // alphabets within digits
        assertFalse(NokPhone.isValidNokPhone("9312 1534")); // spaces within digits

        // valid next of kin phone numbers
        assertTrue(NokPhone.isValidNokPhone("911")); // exactly 3 numbers
        assertTrue(NokPhone.isValidNokPhone("93121534"));
        assertTrue(NokPhone.isValidNokPhone("12429384203315623")); // long phone numbers
    }
}

