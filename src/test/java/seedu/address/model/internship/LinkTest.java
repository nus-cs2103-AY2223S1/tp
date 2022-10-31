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
        assertFalse(Link.isValidLink("htt://google.com")); // invalid scheme
        assertFalse(Link.isValidLink("https://www")); // missing domain
        assertFalse(Link.isValidLink("https://www.example.c")); // domain is below min length (2 chars)
        assertFalse(Link.isValidLink("https://www.example.comcomcom")); // domain exceeds max length (8 chars)
        assertFalse(Link.isValidLink("https://www."
                + "exampleexampleexampleexampleexampleexampleexampleexampleexampleexampleexampleexampleexampleexample"
                + "exampleexampleexampleexampleexampleexampleexampleexampleexampleexampleexampleexampleexampleexample"
                + "exampleexampleexampleexampleexample" + ".com")); // hostname exceeds max length (227 chars)
        assertFalse(Link.isValidLink("https://www.example%.com")); // invalid characters in hostname ("%")
        assertFalse(Link.isValidLink("https://www.example.com%")); // invalid characters in domain ("%")

        // valid links
        assertTrue(Link.isValidLink("google.com")); // without scheme
        assertTrue(Link.isValidLink("https://google.com")); // with scheme
        assertTrue(Link.isValidLink("https://careers.google.com")); // with domain at the front
        assertTrue(Link.isValidLink("https://careers.google.com/")); // with "/"
        assertTrue(Link.isValidLink("https://careers.google.com/students")); // with path
        assertTrue(Link.isValidLink("https://careers.google.com.sg")); // with multiple domains

    }
}
