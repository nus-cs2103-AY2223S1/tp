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
        assertFalse(Phone.isValidNokPhone("")); // empty string
        assertFalse(Phone.isValidNokPhone(" ")); // spaces only
        assertFalse(Phone.isValidNokPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidNokPhone(" phone")); // non-numeric
        assertFalse(Phone.isValidNokPhone("9011s041")); // alphabets within digits
        assertFalse(Phone.isValidNokPhone("9312 1534")); // spaces within digits

        // valid next of kin phone numbers
        assertTrue(Phone.isValidNokPhone("911")); // exactly 3 numbers
        assertTrue(Phone.isValidNokPhone("93121534"));
        assertTrue(Phone.isValidNokPhone("12429384203315623")); // long phone numbers
    }
}

