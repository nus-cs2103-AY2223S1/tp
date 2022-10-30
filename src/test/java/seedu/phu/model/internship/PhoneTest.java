package seedu.phu.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.phu.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PhoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Phone(null));
    }

    @Test
    public void constructor_invalidPhone_throwsIllegalArgumentException() {
        String invalidPhone = "";
        assertThrows(IllegalArgumentException.class, () -> new Phone(invalidPhone));
    }

    @Test
    public void isValidPhone() {
        // null phone number
        assertThrows(NullPointerException.class, () -> Phone.isValidPhone(null));

        // invalid phone numbers
        assertFalse(Phone.isValidPhone("")); // empty string
        assertFalse(Phone.isValidPhone(" ")); // spaces only
        assertFalse(Phone.isValidPhone("91")); // less than 3 numbers
        assertFalse(Phone.isValidPhone("1242938420331235")); // more than 15 numbers
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("9312 1534")); // spaces within area code and phone number
        assertFalse(Phone.isValidPhone("+ 93121534")); // + followed with empty space
        assertFalse(Phone.isValidPhone("+1  93121534")); // 2 whitespace in between country code and the rest
        assertFalse(Phone.isValidPhone("+6255 93121534")); // with country code (4 digit)
        assertFalse(Phone.isValidPhone("+625 1242938420331235")); // more than 15 numbers with country code and space
        assertFalse(Phone.isValidPhone("+6251242938420331235")); // more than 15 numbers with country code

        // valid phone numbers
        assertTrue(Phone.isValidPhone("+1 93121534")); // with country code (1 digit)
        assertTrue(Phone.isValidPhone("+65 93121534")); // with country code (2 digit)
        assertTrue(Phone.isValidPhone("+625 93121534")); // with country code (3 digit)
        assertTrue(Phone.isValidPhone("+625 124293842033123")); // max length with country code with space
        assertTrue(Phone.isValidPhone("+625124293842033123")); // max length with country code without space
        assertTrue(Phone.isValidPhone("+1 123")); // min length with country code with space
        assertTrue(Phone.isValidPhone("+6251")); // min length with country code without space
        assertTrue(Phone.isValidPhone("911")); // exactly 3 numbers (min length)
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("124293842033123")); // // exactly 15 numbers (max length)
    }
}
