package seedu.address.model.team;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class LinkNameTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new LinkName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new LinkName(invalidName));
    }

    @Test
    public void isValidLinkName() {
        // null name
        assertThrows(NullPointerException.class, () -> LinkName.isValidLinkName(null));

        // invalid name
        assertFalse(LinkName.isValidLinkName("")); // empty string
        assertFalse(LinkName.isValidLinkName(" ")); // spaces only
        assertFalse(LinkName.isValidLinkName("^")); // only non-alphanumeric characters
        assertFalse(LinkName.isValidLinkName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(LinkName.isValidLinkName("peter jack")); // alphabets only
        assertTrue(LinkName.isValidLinkName("12345")); // numbers only
        assertTrue(LinkName.isValidLinkName("peter the 2nd")); // alphanumeric characters
        assertTrue(LinkName.isValidLinkName("Capital Tan")); // with capital letters
        assertTrue(LinkName.isValidLinkName("David Roger Jackson Ray Jr 2nd")); // long names
    }

}