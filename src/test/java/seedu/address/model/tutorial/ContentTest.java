package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

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

        // invalid content
        assertFalse(Content.isValidContent("")); // empty string
        assertFalse(Content.isValidContent(" ")); // spaces only
        assertFalse(Content.isValidContent("^")); // only non-alphanumeric characters
        assertFalse(Content.isValidContent("uml*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Content.isValidContent("uml diagram")); // alphabets only
        assertTrue(Content.isValidContent("12345")); // numbers only
        assertTrue(Content.isValidContent("uml the 2nd")); // alphanumeric characters
        assertTrue(Content.isValidContent("UML Diagram")); // with capital letters
        assertTrue(Content.isValidContent("Unified Modeling Language Diagram")); // long content
    }
}
