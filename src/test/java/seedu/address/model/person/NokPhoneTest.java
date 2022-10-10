package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NOKPhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NOKPhone(null));
    }

    @Test
    public void constructor_invalidNOKPhone_throwsIllegalArgumentException() {
        String invalidNOKPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new NOKPhone(invalidNOKPhone));
    }

    @Test
    public void isValidNOKPhone() {
        // null next of kin phone number
        assertThrows(NullPointerException.class, () -> NOKPhone.isValidNOKPhone(null));

        // invalid next of kin phone numbers
        assertFalse(NOKPhone.isValidNOKPhone("")); // empty string
        assertFalse(NOKPhone.isValidNOKPhone(" ")); // spaces only
        assertFalse(NOKPhone.isValidNOKPhone("91")); // less than 3 numbers
        assertFalse(NOKPhone.isValidNOKPhone(" phone")); // non-numeric
        assertFalse(NOKPhone.isValidNOKPhone("9011s041")); // alphabets within digits
        assertFalse(NOKPhone.isValidNOKPhone("9312 1534")); // spaces within digits

        // valid next of kin phone numbers
        assertTrue(NOKPhone.isValidNOKPhone("911")); // exactly 3 numbers
        assertTrue(NOKPhone.isValidNOKPhone("93121534"));
        assertTrue(NOKPhone.isValidNOKPhone("12429384203315623")); // long phone numbers
    }
}

