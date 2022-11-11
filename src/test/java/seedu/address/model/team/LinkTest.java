package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_FACEBOOK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_URL_FACEBOOK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.LINK_FACEBOOK;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.LinkBuilder;

class LinkTest {

    @Test
    public void null_constructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null, null));
    }

    @Test
    public void isSameLink() {
        // same object -> returns true
        assertTrue(LINK_GOOGLE.isSameLink(LINK_GOOGLE));

        // null -> returns false
        assertFalse(LINK_GOOGLE.isSameLink(null));

        // same name, all other attributes different -> returns true
        Link editedGoogle = new LinkBuilder(LINK_GOOGLE).withLink(VALID_URL_FACEBOOK).build();
        assertTrue(LINK_GOOGLE.isSameLink(editedGoogle));

        // different name, all other attributes same -> returns false
        editedGoogle = new LinkBuilder(LINK_GOOGLE).withName(VALID_NAME_FACEBOOK).build();
        assertFalse(LINK_GOOGLE.isSameLink(editedGoogle));

        // name differs in case, all other attributes same -> returns false
        Link editedFacebook = new LinkBuilder(LINK_FACEBOOK).withName(VALID_NAME_FACEBOOK.toUpperCase()).build();
        assertFalse(LINK_FACEBOOK.isSameLink(editedFacebook));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_FACEBOOK + " ";
        editedFacebook = new LinkBuilder(LINK_FACEBOOK).withName(nameWithTrailingSpaces).build();
        assertFalse(LINK_FACEBOOK.isSameLink(editedFacebook));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Link googleCopy = new LinkBuilder(LINK_GOOGLE).build();
        assertTrue(LINK_GOOGLE.equals(googleCopy));

        // same object -> returns true
        assertTrue(LINK_GOOGLE.equals(LINK_GOOGLE));

        // null -> returns false
        assertFalse(LINK_GOOGLE.equals(null));

        // different type -> returns false
        assertFalse(LINK_GOOGLE.equals(5));

        // different link -> returns false
        assertFalse(LINK_GOOGLE.equals(LINK_FACEBOOK));

        // different name -> returns false
        Link editedGoogle = new LinkBuilder(LINK_GOOGLE).withName(VALID_NAME_FACEBOOK).build();
        assertFalse(LINK_GOOGLE.equals(editedGoogle));

        // different link -> returns false
        editedGoogle = new LinkBuilder(LINK_GOOGLE).withLink(VALID_URL_FACEBOOK).build();
        assertFalse(LINK_GOOGLE.equals(editedGoogle));
    }
}
