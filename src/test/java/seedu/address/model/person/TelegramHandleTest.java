package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TelegramHandleTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TelegramHandle(null));
    }

    @Test
    public void constructor_invalidTelegramHandle_throwsIllegalArgumentException() {
        String invalidTelegramHandle = "";
        assertThrows(IllegalArgumentException.class, () -> new TelegramHandle(invalidTelegramHandle));
    }

    @Test
    public void isValidTelegramHandle() {
        // null Student ID
        assertThrows(NullPointerException.class, () -> TelegramHandle.isValidTelegramHandle(null));

        // invalid Student IDs
        assertFalse(TelegramHandle.isValidTelegramHandle("")); // empty string
        assertFalse(TelegramHandle.isValidTelegramHandle(" ")); // spaces only
        assertFalse(TelegramHandle.isValidTelegramHandle("@when")); // less than 6 characters
        assertFalse(TelegramHandle.isValidTelegramHandle("good_user")); // wrong format
        assertFalse(TelegramHandle.isValidTelegramHandle("@good_user!")); // Invalid Character
        assertFalse(TelegramHandle.isValidTelegramHandle("@good_user%")); // Invalid Character
        assertFalse(TelegramHandle.isValidTelegramHandle("@good_^user")); // Invalid Character
        assertFalse(TelegramHandle.isValidTelegramHandle("@%good_user")); // Invalid Character
        assertFalse(TelegramHandle.isValidTelegramHandle(" @good_user")); // spaces before handle
        assertFalse(TelegramHandle.isValidTelegramHandle("@good_user ")); // spaces after handle
        assertFalse(TelegramHandle.isValidTelegramHandle("@good_ user")); // spaces between
        assertFalse(TelegramHandle.isValidTelegramHandle("@@good_user")); // Extra @ character

        // valid Student IDs
        assertTrue(TelegramHandle.isValidTelegramHandle("@good_user"));
        assertTrue(TelegramHandle.isValidTelegramHandle("@gooduser"));
        assertTrue(TelegramHandle.isValidTelegramHandle("@BestUser"));
    }
}
