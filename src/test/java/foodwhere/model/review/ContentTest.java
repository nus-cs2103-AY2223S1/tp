package foodwhere.model.review;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class ContentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Content(null));
    }

    @Test
    public void constructor_invalidContent_throwsIllegalArgumentException() {
        String invalidContent = "";
        assertThrows(IllegalArgumentException.class, () -> new Content(invalidContent));
    }

    @Test
    public void isValidContent() {
        // null content
        assertThrows(NullPointerException.class, () -> Content.isValidContent(null));

        // invalid contents
        assertFalse(Content.isValidContent("")); // empty string
        assertFalse(Content.isValidContent(" ")); // spaces only

        // valid contents
        assertTrue(Content.isValidContent("Good, 4/5"));
        assertTrue(Content.isValidContent("-")); // one character
        // long content
        assertTrue(Content.isValidContent("The owner is friendly, the food is good. 100% will visit again."));
    }
}
