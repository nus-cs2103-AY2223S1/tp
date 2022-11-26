package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Role(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Role(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Role.isValidRole(null));

        // invalid name
        assertFalse(Role.isValidRole("")); // empty string
        assertFalse(Role.isValidRole(" ")); // spaces only
        assertFalse(Role.isValidRole("^")); // only non-alphanumeric characters
        assertFalse(Role.isValidRole("frontend engineer*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Role.isValidRole("Engineer")); // alphabets only
        assertTrue(Role.isValidRole("12345")); // numbers only
        assertTrue(Role.isValidRole("5th Engineer")); // alphanumeric characters
        assertTrue(Role.isValidRole("Backend Developer")); // with capital letters
        assertTrue(Role.isValidRole("Backend Developer Software Engineer Quant Trader CEO")); // long names
    }

}
