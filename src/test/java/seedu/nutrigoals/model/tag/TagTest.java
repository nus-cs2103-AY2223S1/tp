package seedu.nutrigoals.model.tag;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String emptyTag = "";
        String snack = "snack";
        assertThrows(IllegalArgumentException.class, () -> new Tag(emptyTag));
        assertThrows(IllegalArgumentException.class, () -> new Tag(snack));
    }

    @Test
    public void constructor_caseInsensitiveTagName_success() {
        String breakfast = "BrEakFast";
        String lunch = "LUNCH";
        String dinner = "dinner";
        assertDoesNotThrow(() -> new Tag(breakfast));
        assertDoesNotThrow(() -> new Tag(lunch));
        assertDoesNotThrow(() -> new Tag(dinner));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isValidTagName_validTagWithLeadingWhiteSpace_success() {
        String breakfast = "   breakfast";
        String lunch = "lunch   ";
        String dinner = "  dinner  ";
        assertTrue(Tag.isValidTagName(breakfast));
        assertTrue(Tag.isValidTagName(lunch));
        assertTrue(Tag.isValidTagName(dinner));
    }

}
