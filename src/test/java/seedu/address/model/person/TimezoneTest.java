package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimezoneTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Timezone(null));
    }

    @Test
    public void constructor_invalidTimezone_throwsIllegalArgumentException() {
        String invalidTimezone = "";
        assertThrows(IllegalArgumentException.class, () -> new Timezone(invalidTimezone));
    }

    @Test
    public void isValidTimezone() {
        // null address
        assertThrows(NullPointerException.class, () -> Timezone.isValidTimezone(null));

        // invalid addresses
        assertFalse(Timezone.isValidTimezone("")); // empty string
        assertFalse(Timezone.isValidTimezone(" ")); // spaces only

        // valid addresses
        assertTrue(Timezone.isValidTimezone("+8"));
        assertTrue(Timezone.isValidTimezone("-8")); // negative
        assertTrue(Timezone.isValidTimezone("+18")); // boundary value
        assertTrue(Timezone.isValidTimezone("-18")); // boundary value
    }
}
