package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class InternshipRoleTest {

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new InternshipRole(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> InternshipRole.isValidName(null));

        // invalid name
        assertFalse(InternshipRole.isValidName("")); // empty string
        assertFalse(InternshipRole.isValidName(" ")); // spaces only
        assertFalse(InternshipRole.isValidName("^")); // only non-alphanumeric characters
        assertFalse(InternshipRole.isValidName("job*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(InternshipRole.isValidName("engineer")); // alphabets only
        assertTrue(InternshipRole.isValidName("12345")); // numbers only
        assertTrue(InternshipRole.isValidName("2nd lead engineer")); // alphanumeric characters
        assertTrue(InternshipRole.isValidName("Data Analyst")); // with capital letters
        assertTrue(InternshipRole.isValidName("Frontend and Backend Senior Project Lead Engineer")); // long names
    }
}
