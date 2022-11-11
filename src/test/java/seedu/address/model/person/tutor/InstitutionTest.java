package seedu.address.model.person.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InstitutionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Institution(null));
    }

    @Test
    public void constructor_invalidInstitution_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Institution(invalidName));
    }

    @Test
    public void isValidInstitution() {
        // null name
        assertThrows(NullPointerException.class, () -> Institution.isValidInstitution(null));

        // invalid name
        assertFalse(Institution.isValidInstitution("")); // empty string
        assertFalse(Institution.isValidInstitution(" ")); // spaces only
        assertFalse(Institution.isValidInstitution("^")); // only non-alphanumeric characters
        assertFalse(Institution.isValidInstitution("nus*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Institution.isValidInstitution("national university of singapore")); // alphabets only
        assertTrue(Institution.isValidInstitution("12345")); // numbers only
        assertTrue(Institution.isValidInstitution("peter the 2nd")); // alphanumeric characters
        assertTrue(Institution.isValidInstitution("Capital Tan")); // with capital letters
        assertTrue(Institution.isValidInstitution("national university of singapore 2nd")); // long names
    }
}
