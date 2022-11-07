package seedu.trackascholar.model.applicant;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.trackascholar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Name(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Name(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(Name.isValidName("")); // empty string
        assertFalse(Name.isValidName(" ")); // spaces only
        assertFalse(Name.isValidName("^")); // only non-alphanumeric characters
        assertFalse(Name.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Name.isValidName("peter jack")); // alphabets only
        assertTrue(Name.isValidName("12345")); // numbers only
        assertTrue(Name.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(Name.isValidName("Capital Tan")); // with capital letters
        assertTrue(Name.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    public void equalsIgnoreCase() {
        String firstName = "peter";
        String firstNameMixedCase = "PeTeR";
        String secondName = "jack";
        String secondNameMixedCase = "jACk";

        // same name
        assertTrue(new Name(firstName).equalsIgnoreCase(new Name(firstName)));
        assertTrue(new Name(secondName).equalsIgnoreCase(new Name(secondName)));

        // same name but mixed case
        assertTrue(new Name(firstName).equalsIgnoreCase(new Name(firstNameMixedCase)));
        assertTrue(new Name(secondName).equalsIgnoreCase(new Name(secondNameMixedCase)));

        // different name
        assertFalse(new Name(firstName).equalsIgnoreCase(new Name(secondName)));

        // different name with mixed case
        assertFalse(new Name(firstName).equalsIgnoreCase(new Name(secondNameMixedCase)));
        assertFalse(new Name(secondName).equalsIgnoreCase(new Name(firstNameMixedCase)));
        assertFalse(new Name(firstNameMixedCase).equalsIgnoreCase(new Name(secondNameMixedCase)));
    }
}
