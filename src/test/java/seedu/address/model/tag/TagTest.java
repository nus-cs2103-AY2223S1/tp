package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

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

        // multiple tag words
        assertTrue(Tag.isValidTagName("in progress"));
        assertTrue(Tag.isValidTagName("Check limit of 50 works by using a long input here"));

        //Word and numbers
        assertTrue(Tag.isValidTagName("123456"));
        assertTrue(Tag.isValidTagName("Valid 123456"));

        //Symbols not allowed
        assertFalse(Tag.isValidTagName("!!!"));
        assertFalse(Tag.isValidTagName("Symb@ls not allowed."));

        //Limit between 1-50 characters
        assertFalse(Tag.isValidTagName("Here is a very long tag line to input to check that it fails"));
    }

}
