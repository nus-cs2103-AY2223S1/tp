package seedu.address.model.module;

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
    public void isValidLinkName() {
        // null link name
        assertThrows(NullPointerException.class, () -> Link.isValidLinkName(null));
    }
}
