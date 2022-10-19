package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SocialTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Social(null));
    }

    @Test
    public void isValidSocial() {
        // null social handle
        assertThrows(NullPointerException.class, () -> Social.isValidSocial(null));

        // invalid social handles
        assertFalse(Social.isValidSocial(" ")); // spaces only
        assertFalse(Social.isValidSocial("@username")); // no platform
        assertFalse(Social.isValidSocial("facebook@")); // no handle
        assertFalse(Social.isValidSocial("instagram@username@2002")); // multiple '@'s

        // valid social handles
        assertTrue(Social.isValidSocial("snapchat@hellokitty")); // alphabets only
        assertTrue(Social.isValidSocial("2002@123456")); // numbers only
        assertTrue(Social.isValidSocial("facebook@user123")); // alphanumeric
    }
}
