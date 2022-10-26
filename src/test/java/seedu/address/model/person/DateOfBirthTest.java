package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateOfBirthTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateOfBirth(null));
    }

    @Test
    public void constructor_invalidDateOfBirth_throwsIllegalArgumentException() {
        String invalidDateOfBirth = "";
        assertThrows(IllegalArgumentException.class, () -> new DateOfBirth(invalidDateOfBirth));
    }

    @Test
    public void isValidDateOfBirth() {
        // null DateOfBirth
        assertThrows(NullPointerException.class, () -> DateOfBirth.isValidDateOfBirth(null));

        // blank DateOfBirth
        assertFalse(DateOfBirth.isValidDateOfBirth("")); // empty string
        assertFalse(DateOfBirth.isValidDateOfBirth(" ")); // spaces only

        // invalid DateOfBirth
        assertFalse(DateOfBirth.isValidDateOfBirth("1.1.2000"));
        assertFalse(DateOfBirth.isValidDateOfBirth("12/23/2000"));
        assertFalse(DateOfBirth.isValidDateOfBirth("12 Mar 1993"));

        // valid DateOfBirth
        assertTrue(DateOfBirth.isValidDateOfBirth("1/1/1000"));
        assertTrue(DateOfBirth.isValidDateOfBirth("23/12/2000"));
        assertTrue(DateOfBirth.isValidDateOfBirth("2/11/1900"));
        assertTrue(DateOfBirth.isValidDateOfBirth("11/11/1111"));
    }
}
