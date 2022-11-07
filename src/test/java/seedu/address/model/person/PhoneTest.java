package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.attribute.Phone;

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
        assertFalse(Phone.isValidPhone("phone")); // non-numeric
        assertFalse(Phone.isValidPhone("9011p041")); // alphabets within digits
        assertFalse(Phone.isValidPhone("+54553 123123")); // country code max 4 digit
        assertFalse(Phone.isValidPhone("+543 12312 123123")); // area code max 4 digit

        // valid phone numbers
        assertTrue(Phone.isValidPhone("65 123123")); // optional + for contry code
        assertTrue(Phone.isValidPhone("+543  123123")); // additional spacings
        assertTrue(Phone.isValidPhone("111")); // short number
        assertTrue(Phone.isValidPhone("+65 83010000")); // with country code
        assertTrue(Phone.isValidPhone("+1 133 10230002")); // with country code and area code
        assertTrue(Phone.isValidPhone("+1113 3133 102")); // with country code and area code
        assertTrue(Phone.isValidPhone("+1113 3133 102123123123123")); // with country code and area code
        assertTrue(Phone.isValidPhone("11115869"));
        assertTrue(Phone.isValidPhone("124293842033123")); // long phone numbers
        assertTrue(Phone.isValidPhone("9312 1534")); // spaces within digits
        assertTrue(Phone.isValidPhone("81115869"));
        assertTrue(Phone.isValidPhone("93121534"));
        assertTrue(Phone.isValidPhone("63159560"));
    }
}
