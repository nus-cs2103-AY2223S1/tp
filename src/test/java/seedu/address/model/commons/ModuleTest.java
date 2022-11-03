package seedu.address.model.commons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleCode(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleCode(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ModuleCode.isValidModule(null));

        // invalid name
        assertFalse(ModuleCode.isValidModule("")); // empty string
        assertFalse(ModuleCode.isValidModule(" ")); // spaces only
        assertFalse(ModuleCode.isValidModule("^")); // only non-alphanumeric characters
        assertFalse(ModuleCode.isValidModule("peter*")); // contains non-alphanumeric characters
        assertFalse(ModuleCode.isValidModule("peter jack")); // alphabets only
        assertFalse(ModuleCode.isValidModule("12345")); // numbers only
        assertFalse(ModuleCode.isValidModule("peter the 2nd")); // alphanumeric characters
        assertFalse(ModuleCode.isValidModule("Capital Tan")); // with capital letters
        assertFalse(ModuleCode.isValidModule("David Roger Jackson Ray Jr 2nd")); // long names

        // valid name
        assertTrue(ModuleCode.isValidModule("CS1101"));
        assertTrue(ModuleCode.isValidModule("CS1101S"));
        assertTrue(ModuleCode.isValidModule("DSE1212"));
        assertTrue(ModuleCode.isValidModule("AAA1111A"));
        assertTrue(ModuleCode.isValidModule("AAA1111"));

    }
}
