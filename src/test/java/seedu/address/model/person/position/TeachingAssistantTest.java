package seedu.address.model.person.position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TeachingAssistantTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TeachingAssistant(null));
    }

    @Test
    public void constructor_invalidAvailability_throwsIllegalArgumentException() {
        String invalidAvailability = "avail";
        assertThrows(IllegalArgumentException.class, () -> new TeachingAssistant(invalidAvailability));
    }

    @Test
    public void isValidAvailability() {
        // null availability
        assertThrows(NullPointerException.class, () -> TeachingAssistant.isValidAvailability(null));

        // invalid availability
        assertFalse(TeachingAssistant.isValidAvailability("")); // empty string
        assertFalse(TeachingAssistant.isValidAvailability(" ")); // spaces only
        assertFalse(TeachingAssistant.isValidAvailability("string")); // random string
        assertFalse(TeachingAssistant.isValidAvailability("123")); // numbers
        assertFalse(TeachingAssistant.isValidAvailability("availablee")); // incorrect spelling
        assertFalse(TeachingAssistant.isValidAvailability("ununavailable")); // incorrect spelling
        assertFalse(TeachingAssistant.isValidAvailability(" available ")); // random spaces

        // valid availability
        assertTrue(TeachingAssistant.isValidAvailability("available")); // small cases
        assertTrue(TeachingAssistant.isValidAvailability("Available")); // beginning with upper case
        assertTrue(TeachingAssistant.isValidAvailability("Unavailable")); // similar, for unavailable
        assertTrue(TeachingAssistant.isValidAvailability("AVAILABLE")); // all upper cases
        assertTrue(TeachingAssistant.isValidAvailability("uNaVaIlAbLe")); // mix of lower and upper cases

    }
}
