package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null));
    }

    @Test
    public void constructor_invalidLink_throwsIllegalArgumentException() {
        String invalidLink = "google.";
        assertThrows(IllegalArgumentException.class, () -> new Link(invalidLink));
    }

    @Test
    public void isValidLink() {
        // null link
        assertThrows(NullPointerException.class, () -> Link.isValidLink(null));

        // invalid links
        assertFalse(Link.isValidLink("")); // empty string
        assertFalse(Link.isValidLink(" ")); // spaces only
        assertFalse(Link.isValidLink("google")); // no '.'
        assertFalse(Link.isValidLink("https://www")); // missing domain

        // valid links
        assertTrue(Link.isValidLink("google.com")); // without scheme
        assertTrue(Link.isValidLink("https://www.google.com")); // with scheme
        assertTrue(Link.isValidLink("https://www.google.com/careers")); // with path
    }
}
