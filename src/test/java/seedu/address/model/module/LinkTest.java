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
    public void isValidLinkName_linkWithHttps() {
        String linkUrl = "https://luminus.edu.sg";
        assertEquals(true, Link.isValidLinkName(linkUrl));
    }

    @Test
    public void isValidLinkName_linkWithoutHttps() {
        String linkUrl = "outlook.office.com/mail/";
        assertEquals(true, Link.isValidLinkName(linkUrl));
    }

    @Test
    public void isValidLinkName_linkWithWhitespace() {
        String linkUrl = "git hub.com";
        assertEquals(false, Link.isValidLinkName(linkUrl));
    }

    @Test
    public void isValidLinkName_onlyWhitespace() {
        String linkUrl = "           ";
        assertEquals(false, Link.isValidLinkName(linkUrl));
    }

    @Test
    public void isValidLinkName_linkWithSpecialCharacters() {
        String linkUrl = "https://¯\\_(ツ)_/¯.com";
        assertEquals(false, Link.isValidLinkName(linkUrl));
    }

    @Test
    public void isValidLinkName_linkWithNoDomain() {
        String linkUrl = "https://googlecom";
        assertEquals(false, Link.isValidLinkName(linkUrl));
    }
}
