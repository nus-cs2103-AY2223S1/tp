package seedu.pennywise.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class EntryTypeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new EntryType((null)));
    }

    @Test
    public void constructor_invalidEntryType_throwsIllegalArgumentException() {
        String invalidEntryType = "c";
        assertThrows(IllegalArgumentException.class, () -> new EntryType(invalidEntryType));
    }

    @Test
    public void isValidEntryType() {
        // null entry type
        assertThrows(NullPointerException.class, () -> EntryType.isValidEntryType(null));

        // invalid entry type
        assertFalse(EntryType.isValidEntryType(""));
        assertFalse(EntryType.isValidEntryType("c"));

        // valid expenditure type
        String validEntryType = "e";
        assertTrue(EntryType.isValidEntryType(validEntryType));

        // valid income type
        validEntryType = "i";
        assertTrue(EntryType.isValidEntryType(validEntryType));
    }
}
