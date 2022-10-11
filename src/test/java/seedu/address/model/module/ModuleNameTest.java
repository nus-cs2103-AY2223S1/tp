package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleName(null));
    }

    @Test
    public void constructor_invalidModuleName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> ModuleName.isValidName(null));

        // invalid name
        assertFalse(ModuleName.isValidName("")); // empty string
        assertFalse(ModuleName.isValidName(" ")); // spaces only
        assertFalse(ModuleName.isValidName("^")); // only non-alphanumeric characters
        assertFalse(ModuleName.isValidName("cs*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ModuleName.isValidName("cs makeup test")); // alphabets only
        assertTrue(ModuleName.isValidName("computer science 1st intro mod")); // alphanumeric characters
        assertTrue(ModuleName.isValidName("Computer Science")); // with capital letters
    }
}
