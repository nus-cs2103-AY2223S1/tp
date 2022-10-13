package seedu.address.model.person.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SlackTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Slack(null));
    }

    @Test
    public void constructor_invalidSlack_throwsIllegalArgumentException() {
        String invalidSlack = "";
        assertThrows(IllegalArgumentException.class, () -> new Slack(invalidSlack));
    }

    @Test
    public void isValidSlack() {
        // null address
        assertThrows(NullPointerException.class, () -> Slack.isValidSlack(null));

        // invalid addresses
        assertFalse(Slack.isValidSlack("")); // empty string
        assertFalse(Slack.isValidSlack(" ")); // spaces only
        assertFalse(Slack.isValidSlack("@rachel")); // have invalid symbol @

        // valid addresses
        assertTrue(Slack.isValidSlack("rachel"));
        assertTrue(Slack.isValidSlack("rach_el")); // with underscore
    }
}

