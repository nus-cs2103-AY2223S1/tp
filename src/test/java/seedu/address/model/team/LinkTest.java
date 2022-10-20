package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalLinks.LINK_FACEBOOK;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE;
import static seedu.address.testutil.TypicalLinks.LINK_GOOGLE_DUPLICATED;

import org.junit.jupiter.api.Test;

class LinkTest {

    @Test
    public void null_constructor_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Link(null, null));
    }


    @Test
    public void equals() {
        //Link must equals itself
        assertTrue(LINK_GOOGLE.equals(LINK_GOOGLE));

        //Link equals any links with same name and link.
        assertTrue(LINK_GOOGLE.equals(LINK_GOOGLE_DUPLICATED));

        //Link does not equal links with different name and link
        assertFalse(LINK_GOOGLE.equals(LINK_FACEBOOK));

        //Link does not equal null
        assertFalse(LINK_GOOGLE.equals(null));

        //Link does not equal any other type
        assertFalse(LINK_GOOGLE.equals(5));
    }
}
