package seedu.address.model.person.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class QualificationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Qualification(null));
    }

    @Test
    public void constructor_invalidQualification_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Qualification(invalidName));
    }

    @Test
    public void isValidQualification() {
        // null name
        assertThrows(NullPointerException.class, () -> Qualification.isValidQualification(null));

        // invalid name
        assertFalse(Qualification.isValidQualification("")); // empty string
        assertFalse(Qualification.isValidQualification(" ")); // spaces only
        assertFalse(Qualification.isValidQualification("^")); // only non-alphanumeric characters
        assertFalse(Qualification.isValidQualification("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Qualification.isValidQualification("peter jack")); // alphabets only
        assertTrue(Qualification.isValidQualification("12345")); // numbers only
        assertTrue(Qualification.isValidQualification("peter the 2nd")); // alphanumeric characters
        assertTrue(Qualification.isValidQualification("Capital Tan")); // with capital letters
        assertTrue(Qualification.isValidQualification("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
