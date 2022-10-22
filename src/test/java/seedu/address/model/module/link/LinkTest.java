package seedu.address.model.module.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class LinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null, null));
    }

    @Test
    public void constructor_invalidLinkUrl_throwsIllegalArgumentException() {
        String invalidLinkUrl = "";
        String validLinkAlias = "nice";
        assertThrows(IllegalArgumentException.class, () -> new Link(validLinkAlias, invalidLinkUrl));
    }

    @Test
    public void constructor_invalidLinkAlias_throwsIllegalArgumentException() {
        String validLinkUrl = "https://luminus.edu.sg";
        String invalidLinkAlias = "///";
        assertThrows(IllegalArgumentException.class, () -> new Link(invalidLinkAlias, validLinkUrl));
    }

    @Test
    public void isInvalidLinkAlias_throwsNullPointerException() {
        // null link alias
        assertThrows(NullPointerException.class, () -> Link.isValidLinkAlias(null));
    }

    @Test
    public void isValidLinkAlias_linkAliasWithOnlyAlphabets() {
        String linkAlias = "nus";
        assertEquals(true, Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isValidLinkAlias_linkAliasWithOnlyNumbers() {
        String linkAlias = "5555";
        assertEquals(true, Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isValidLinkAlias_linkAliasAlphanumericAndWhitespace() {
        String linkAlias = "ha ha 5";
        assertEquals(true, Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isInvalidLinkAlias_linkAliasWithOnlyWhitespace() {
        String linkAlias = "     ";
        assertEquals(false, Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isInvalidLinkAlias_linkAliasWithSpecialCharacters() {
        String linkAlias = "////";
        assertEquals(false, Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isInvalidLinkUrl_throwsNullPointerException() {
        // null link url
        assertThrows(NullPointerException.class, () -> Link.isValidLinkUrl(null));
    }

    @Test
    public void isValidLinkUrl_linkUrlWithHttps() {
        String linkUrl = "https://luminus.edu.sg";
        assertEquals(true, Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isValidLinkUrl_linkUrlWithoutHttps() {
        String linkUrl = "outlook.office.com/mail/";
        assertEquals(true, Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isInvalidLinkUrl_linkUrlWithWhitespace() {
        String linkUrl = "git hub.com";
        assertEquals(false, Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isInvalidLinkUrl_linkUrlWithOnlyWhitespace() {
        String linkUrl = "           ";
        assertEquals(false, Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isInvalidLinkUrl_linkUrlWithSpecialCharacters() {
        String linkUrl = "https://¯\\_(ツ)_/¯.com";
        assertEquals(false, Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isInvalidLinkUrl_linkUrlWithNoDomain() {
        String linkUrl = "https://googlecom";
        assertEquals(false, Link.isValidLinkUrl(linkUrl));
    }
}
