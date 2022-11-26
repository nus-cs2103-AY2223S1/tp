package seedu.workbook.model.tag;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tag(null));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void equals() {
        Tag tagOne = new Tag("Same name");
        Tag tagTwo = new Tag("Same name");

        // Compare two tag objects of same tagName
        assertTrue(tagOne.equals(tagTwo));

        // Compare two same tag objects
        assertTrue(tagOne.equals(tagOne));

    }

}
