package seedu.masslinkers.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.masslinkers.testutil.Assert.assertThrows;

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
        assertThrows(NullPointerException.class, () -> Phone.isCorrect(null));

        // empty phone numbers (after trimming)
        assertTrue(Phone.isEmptyPhone(""));
        assertTrue(Phone.isEmptyPhone("         "));

        // valid phone numbers which passes the Regex pattern match
        assertTrue(Phone.isCorrect("12345678")); // exactly 8 numbers
        assertTrue(Phone.isCorrect("93121534"));
        assertTrue(Phone.isCorrect("+1234567")); // acceptable international number
        assertTrue(Phone.isCorrect("+12345674534534")); // acceptable international number

        // blank phone numbers which are not correct and are not valid inputs
        assertFalse(Phone.isCorrect("")); // empty string
        assertFalse(Phone.isCorrect(" ")); // spaces only

        // "incorrect" phone numbers that fail the Regex pattern match but are still considered valid inputs
        assertFalse(Phone.isCorrect("91")); // less than 7 digits
        assertFalse(Phone.isCorrect("93121534444444444")); // more than 16 characters (length: 17)
        assertFalse(Phone.isCorrect("+9312153444444444")); // more than 16 digits (length: 17)
        assertFalse(Phone.isCorrect("+++++999"));
        assertFalse(Phone.isCorrect("this is a phone")); // non-numeric
        assertFalse(Phone.isCorrect("9011p041")); // alphabets within digits
        assertFalse(Phone.isCorrect("9312 1534")); // spaces within digits
    }
}
