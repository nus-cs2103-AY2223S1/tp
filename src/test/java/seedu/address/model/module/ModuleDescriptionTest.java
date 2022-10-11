package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ModuleDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ModuleDescription(null));
    }

    @Test
    public void constructor_invalidModuleDescription_throwsIllegalArgumentException() {
        String invalidAddress = "";
        assertThrows(IllegalArgumentException.class, () -> new ModuleDescription(invalidAddress));
    }

    @Test
    public void isValidDescription() {
        // null description
        assertThrows(NullPointerException.class, () -> ModuleDescription.isValidDescription(null));

        // invalid descriptions
        assertFalse(ModuleDescription.isValidDescription("")); // empty string
        assertFalse(ModuleDescription.isValidDescription(" ")); // spaces only

        // valid descriptions
        assertTrue(ModuleDescription.isValidDescription("Taken by year 2 cs students"));
        assertTrue(ModuleDescription.isValidDescription("Class of 20 people"));
        assertTrue(ModuleDescription.isValidDescription("Co teaching with Prof Ben"));
        assertTrue(ModuleDescription.isValidDescription("No TAs for this module"));
        assertTrue(ModuleDescription.isValidDescription("No bellcurve module"));
        assertTrue(ModuleDescription.isValidDescription("T")); // one character
        assertTrue(ModuleDescription.isValidDescription("Prerequisites are CS2105 "
                + "CS2107 CS2103T CS2102 CS2101 CS2100")); // long description
    }
}
