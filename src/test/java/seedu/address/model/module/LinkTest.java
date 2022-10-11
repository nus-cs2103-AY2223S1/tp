package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.link.Link;

public class LinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null));
    }

    @Test
    public void constructor_invalidLinkName_throwsIllegalArgumentException() {
        String invalidLinkName = "";
        assertThrows(IllegalArgumentException.class, () -> new Link(invalidLinkName));
    }

    @Test
    public void isValidLinkName_throwsNullPointerException() {
        // null link name
        assertThrows(NullPointerException.class, () -> Link.isValidLinkName(null));
    }

    @Test
    public void isValidLinkName_linkWithHTTPS() {
        String linkURL = "https://luminus.edu.sg";
        assertEquals(true, Link.isValidLinkName(linkURL));
    }

    @Test
    public void isValidLinkName_linkWithoutHTTPS() {
        String linkURL = "outlook.office.com/mail/";
        assertEquals(true, Link.isValidLinkName(linkURL));
    }

    @Test
    public void isValidLinkName_linkWithWhitespace() {
        String linkURL = "git hub.com";
        assertEquals(false, Link.isValidLinkName(linkURL));
    }

    @Test
    public void isValidLinkName_Whitespace() {
        String linkURL = "           ";
        assertEquals(false, Link.isValidLinkName(linkURL));
    }

    @Test
    public void isValidLinkName_linkWithSpecialCharacters() {
        String linkURL = "https://¯\\_(ツ)_/¯.com";
        assertEquals(false, Link.isValidLinkName(linkURL));
    }

    @Test
    public void isValidLinkName_linkWithNoDomain() {
        String linkURL = "https://googlecom";
        assertEquals(false, Link.isValidLinkName(linkURL));
    }
}
