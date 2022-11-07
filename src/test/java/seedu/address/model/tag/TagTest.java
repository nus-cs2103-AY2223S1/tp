package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.Module;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> new Tag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equals() {
        final Tag standardTag = new Tag("friends");

        // same values -> returns true
        Tag tagWithSameName = new Tag("friends");
        assertTrue(standardTag.equals(tagWithSameName));

        // same object -> returns true
        assertTrue(standardTag.equals(standardTag));

        // null -> returns false
        assertFalse(standardTag.equals(null));

        // different types -> returns false
        assertFalse(standardTag.equals(new Module("cs2040s")));

        // different tagNames -> returns false
        assertFalse(standardTag.equals(new Tag("family")));
    }

}
