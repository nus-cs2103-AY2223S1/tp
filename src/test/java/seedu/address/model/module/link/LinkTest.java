package seedu.address.model.module.link;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_ALIAS_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_URL;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_URL_2;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_URL_3;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_MODULE_LINK_URL_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_ALIAS_5;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_4;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_4_WITHOUT_HTTPS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_4_WITH_HTTP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_5_WITH_HTTPS_WWW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_5_WITH_WWW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MODULE_LINK_URL_WITHOUT_HTTPS;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.VALID_LINK_2;
import static seedu.address.testutil.TypicalLinks.VALID_LINK_2_DIFFERENT_ALIAS;
import static seedu.address.testutil.TypicalLinks.VALID_LINK_2_DIFFERENT_URL;
import static seedu.address.testutil.TypicalLinks.VALID_LINK_4;
import static seedu.address.testutil.TypicalLinks.VALID_LINK_4_NO_HTTPS_HEADER;

import org.junit.jupiter.api.Test;

public class LinkTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null, null));
    }

    @Test
    public void constructor_invalidLinkUrl_throwsIllegalArgumentException() {
        String invalidLinkUrl = INVALID_MODULE_LINK_URL;
        String validLinkAlias = VALID_MODULE_LINK_ALIAS_4;
        assertThrows(IllegalArgumentException.class, () -> new Link(validLinkAlias, invalidLinkUrl));
    }

    @Test
    public void constructor_invalidLinkAlias_throwsIllegalArgumentException() {
        String validLinkUrl = VALID_MODULE_LINK_URL_4;
        String invalidLinkAlias = INVALID_MODULE_LINK_ALIAS;
        assertThrows(IllegalArgumentException.class, () -> new Link(invalidLinkAlias, validLinkUrl));
    }

    @Test
    public void isValidLinkAlias() {
        String linkAliasOnlyAlphabets = VALID_MODULE_LINK_ALIAS_4;
        String linkAliasOnlyNumbers = VALID_MODULE_LINK_ALIAS_5;
        String linkAliasAlphanumericWithWhitespace = VALID_MODULE_LINK_ALIAS + "   " + VALID_MODULE_LINK_ALIAS;
        String linkAliasOnlyWhitespace = "       ";
        String linkAliasWithSpecialCharacters = INVALID_MODULE_LINK_ALIAS;
        String linkAliasAlphanumericLong = INVALID_MODULE_LINK_ALIAS_2;

        //alias with only alphabets and less than 15 characters -> returns true
        assertTrue(Link.isValidLinkAlias(linkAliasOnlyAlphabets));

        //alias with only numbers and less than 15 characters -> returns true
        assertTrue(Link.isValidLinkAlias(linkAliasOnlyNumbers));

        //alias which is alphanumeric and whitespace and less than 15 characters -> returns true
        assertTrue(Link.isValidLinkAlias(linkAliasAlphanumericWithWhitespace));

        //alias comprised of multiple whitespace characters only -> returns false
        assertFalse(Link.isValidLinkAlias(linkAliasOnlyWhitespace));

        //alias with special characters -> returns false
        assertFalse(Link.isValidLinkAlias(linkAliasWithSpecialCharacters));

        //alias with alphanumeric characters only but longer than 15 characters -> returns false
        assertFalse(Link.isValidLinkAlias(linkAliasAlphanumericLong));

        assertThrows(NullPointerException.class, () ->Link.isValidLinkAlias(null));
    }

    @Test
    public void isValidLinkUrl() {
        String linkUrlWithHttps = VALID_MODULE_LINK_URL;
        String linkUrlWithoutHttps = VALID_MODULE_LINK_URL_WITHOUT_HTTPS;
        String linkUrlWithOnlyWhitespace = "       ";
        String linkUrlWithWhitespace = INVALID_MODULE_LINK_URL_2;
        String linkUrlWithNoDomain = INVALID_MODULE_LINK_URL_3;
        String linkUrlWithForbiddenSpecialCharacters = INVALID_MODULE_LINK_URL_4;
        String linkUrlWithWww = VALID_MODULE_LINK_URL_5_WITH_WWW;
        String linkUrlWithHttpsWww = VALID_MODULE_LINK_URL_5_WITH_HTTPS_WWW;

        //valid link url with https header -> returns true
        assertTrue(Link.isValidLinkUrl(linkUrlWithHttps));
        //valid link url with no https header -> returns true
        assertTrue(Link.isValidLinkUrl(linkUrlWithoutHttps));
        //link url with only whitespace -> returns false
        assertFalse(Link.isValidLinkUrl(linkUrlWithOnlyWhitespace));
        //link url with whitespace in between -> returns false
        assertFalse(Link.isValidLinkUrl(linkUrlWithWhitespace));
        //link url with no top level domain -> returns false
        assertFalse(Link.isValidLinkUrl(linkUrlWithNoDomain));
        //link url with forbidden special characters -> returns false
        assertFalse(Link.isValidLinkUrl(linkUrlWithForbiddenSpecialCharacters));

        //link url with www prefix -> returns true
        assertTrue(Link.isValidLinkUrl(linkUrlWithWww));

        //link url with https://www prefix -> returns true
        assertTrue(Link.isValidLinkUrl(linkUrlWithHttpsWww));
    }

    @Test
    public void removeLinkUrlProtocol() {
        String linkUrlWithHttps = VALID_MODULE_LINK_URL_4;
        String linkUrlWithHttp = VALID_MODULE_LINK_URL_4_WITH_HTTP;
        String linkUrlWithoutHttps = VALID_MODULE_LINK_URL_4_WITHOUT_HTTPS;

        //String with https header -> returns string without https header
        assertEquals(VALID_MODULE_LINK_URL_4_WITHOUT_HTTPS, Link.removeLinkUrlProtocol(linkUrlWithHttps));

        //String with http header -> returns string without https header
        assertEquals(VALID_MODULE_LINK_URL_4_WITHOUT_HTTPS, Link.removeLinkUrlProtocol(linkUrlWithHttp));

        //String without https header -> return original string
        assertEquals(VALID_MODULE_LINK_URL_4_WITHOUT_HTTPS, Link.removeLinkUrlProtocol(linkUrlWithoutHttps));

        assertThrows(NullPointerException.class, () -> Link.removeLinkUrlProtocol(null));
    }

    @Test
    public void hasLinkAlias() {
        Link link = VALID_LINK_4;

        // link has same link alias as given String -> returns true
        assertTrue(link.hasLinkAlias(VALID_MODULE_LINK_ALIAS_4));

        // link has different link alias as given String -> returns true
        assertFalse(link.hasLinkAlias(VALID_MODULE_LINK_ALIAS_4 + "g"));
    }

    @Test
    public void isSameLinkAlias() {
        Link link2 = VALID_LINK_2;
        Link link2DifferentAlias = VALID_LINK_2_DIFFERENT_URL;
        Link link4 = VALID_LINK_4;

        // same object -> returns true
        assertTrue(link4.isSameLinkAlias(link4));

        // same alias, different object -> returns true
        assertTrue(link2.isSameLinkAlias(link2DifferentAlias));

        // different link alias -> returns false;
        assertFalse(link4.isSameLinkAlias(link2));
    }

    @Test
    public void isSameLinkUrl() {
        Link link2 = VALID_LINK_2;
        Link link2DifferentAlias = VALID_LINK_2_DIFFERENT_ALIAS;
        Link link4 = VALID_LINK_4;
        Link link4NoHttpsHeader = VALID_LINK_4_NO_HTTPS_HEADER;

        // same object -> returns true
        assertTrue(link2.isSameLinkUrlIgnoreProtocol(link2));

        // same URL (both with https), different object and alias -> returns true
        assertTrue(link2.isSameLinkUrlIgnoreProtocol(link2DifferentAlias));

        // same URL (one without https), different object and alias -> returns true
        assertTrue(link4.isSameLinkUrlIgnoreProtocol(link4NoHttpsHeader));

        // different link URL -> returns false;
        assertFalse(link2.isSameLinkUrlIgnoreProtocol(link4));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Link link2 = VALID_LINK_2;
        Link link4 = VALID_LINK_4;
        Link link4WithoutHttpsHeader = VALID_LINK_4_NO_HTTPS_HEADER;

        // same value different object -> returns true
        assertTrue(link4.equals(link4WithoutHttpsHeader));

        // same object -> returns true
        assertTrue(link4.equals(link4));

        // null -> returns false
        assertFalse(link4.equals(null));

        // different type -> returns false
        assertFalse(link4.equals(5));

        // different task description -> returns false
        assertFalse(link2.equals(link4));
    }
}
