package seedu.address.model.person.contact;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;
import seedu.address.testutil.ContactUtil;

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
    public void equals() {
        Slack slack1 = new Slack("test_hello");
        Slack slack2 = new Slack("test_hello");

        assertTrue(slack1.equals(slack1));
        assertTrue(slack1.equals(slack2));
    }

    @Test
    public void equals_invalidInstance_returnsFalse() {
        Slack slack = new Slack("test_hello");
        Name name = new Name("hello");
        assertFalse(slack.equals(name));
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

    @Test
    public void isValidUrl() {
        Slack validSlack = new Slack("hello_world");
        assertTrue(ContactUtil.isValidUrl(validSlack.getLink()));
    }
}

