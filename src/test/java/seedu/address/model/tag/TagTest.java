package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TagTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Tag.convertToTag(null));
    }

    @Test
    public void constructor_invalidTagName_throwsIllegalArgumentException() {
        String invalidTagName = "";
        assertThrows(IllegalArgumentException.class, () -> Tag.convertToTag(invalidTagName));
    }

    @Test
    public void isValidTagName() {
        // null tag name
        assertThrows(NullPointerException.class, () -> Tag.isValidTagName(null));
    }

    @Test
    public void isTagNameCorrect() {
        assertEquals(Tag.EAR.toString(), "Ear");
        assertEquals(Tag.NOSE.toString(), "Nose");
        assertEquals(Tag.THROAT.toString(), "Throat");
    }

    @Test
    public void isTagConversionCorrect() {
        assertThrows(IllegalArgumentException.class, () -> Tag.convertToTag("Eye"));
        assertEquals(Tag.convertToTag("Ear"), Tag.EAR);
    }
}
