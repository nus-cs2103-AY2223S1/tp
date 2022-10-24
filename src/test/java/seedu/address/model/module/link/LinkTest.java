package seedu.address.model.module.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isValidLinkAlias_linkAliasWithOnlyNumbers() {
        String linkAlias = "5555";
        assertTrue(Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isValidLinkAlias_linkAliasAlphanumericAndWhitespace() {
        String linkAlias = "ha ha 5";
        assertTrue(Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isInvalidLinkAlias_linkAliasWithOnlyWhitespace() {
        String linkAlias = "     ";
        assertFalse(Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isInvalidLinkAlias_linkAliasWithSpecialCharacters() {
        String linkAlias = "////";
        assertFalse(Link.isValidLinkAlias(linkAlias));
    }

    @Test
    public void isInvalidLinkUrl_throwsNullPointerException() {
        // null link url
        assertThrows(NullPointerException.class, () -> Link.isValidLinkUrl(null));
    }

    @Test
    public void isValidLinkUrl_linkUrlWithHttps() {
        String linkUrl = "https://luminus.edu.sg";
        assertTrue(Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isValidLinkUrl_linkUrlWithoutHttps() {
        String linkUrl = "outlook.office.com/mail/";
        assertTrue(Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isInvalidLinkUrl_linkUrlWithWhitespace() {
        String linkUrl = "git hub.com";
        assertFalse(Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isInvalidLinkUrl_linkUrlWithOnlyWhitespace() {
        String linkUrl = "           ";
        assertFalse(Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isInvalidLinkUrl_linkUrlWithSpecialCharacters() {
        String linkUrl = "https://¯\\_(ツ)_/¯.com";
        assertFalse(Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void isInvalidLinkUrl_linkUrlWithNoDomain() {
        String linkUrl = "https://googlecom";
        assertFalse(Link.isValidLinkUrl(linkUrl));
    }

    @Test
    public void removeLinkUrlProtocol_linkUrlWithHTTPS() {
        String linkUrl = "https://google.com";
        assertEquals("google.com", Link.removeLinkUrlProtocol(linkUrl));
    }

    @Test
    public void removeLinkUrlProtocol_linkUrlWithHTTP() {
        String linkUrl = "http://google.com";
        assertEquals("google.com", Link.removeLinkUrlProtocol(linkUrl));
    }

    @Test
    public void removeLinkUrlProtocol_linkUrlWithoutHeaderProtocol() {
        String linkUrl = "google.com";
        assertEquals("google.com", Link.removeLinkUrlProtocol(linkUrl));
    }

    @Test
    public void isSameLinkAlias_duplicateLinkAlias() {
        Link link = new Link("gg", "google.com");
        assertTrue(link.isSameLinkAlias(link));
    }

    @Test
    public void isSameLinkAlias_differentLinkAlias() {
        Link link = new Link("gg", "google.com");
        Link link2 = new Link("g", "google.com");
        assertFalse(link.isSameLinkAlias(link2));
    }

    @Test
    public void isSameLinkUrlIgnoreProtocol_sameLinkUrlWithDifferentProtocol() {
        Link link = new Link("gg", "google.com");
        Link link2 = new Link("gg", "https://google.com");
        assertTrue(link.isSameLinkUrlIgnoreProtocol(link2));
    }

    @Test
    public void isSameLinkUrlIgnoreProtocol_sameLinkUrlWithSameProtocol() {
        Link link = new Link("gg", "google.com");
        Link link2 = new Link("gg", "google.com");
        assertTrue(link.isSameLinkUrlIgnoreProtocol(link2));
    }

    @Test
    public void isSameLinkUrlIgnoreProtocol_differentLinkUrlWithSameProtocol() {
        Link link = new Link("gg", "google.com");
        Link link2 = new Link("gg", "goggle.com");
        assertFalse(link.isSameLinkUrlIgnoreProtocol(link2));
    }

    @Test
    public void hasLinkAlias_sameLinkAlias() {
        Link link = new Link("gg", "google.com");
        assertTrue(link.hasLinkAlias("gg"));
    }

    @Test
    public void hasLinkAlias_differentLinkAlias() {
        Link link = new Link("gg", "google.com");
        assertFalse(link.hasLinkAlias("ggg"));
    }
}
