package seedu.address.model.issue;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class UrgencyTest {

    @Test
    public void isValidUrgencyString_error() {
        assertThrows(NullPointerException.class, () -> Urgency.isValidUrgencyString(null));
    }

    @Test
    public void isValidUrgency_success() {
        assertTrue(Urgency.isValidUrgency("0"));
        assertTrue(Urgency.isValidUrgency("1"));
        assertTrue(Urgency.isValidUrgency("3"));
    }

    @Test
    public void isValidUrgencyString_success() {
        assertTrue(Urgency.isValidUrgencyString("LOW"));
        assertTrue(Urgency.isValidUrgencyString("HIGH"));
        assertTrue(Urgency.isValidUrgencyString("MEDIUM"));
    }

    @Test
    public void isValidUrgency_failure() {
        assertFalse(Urgency.isValidUrgency("medium"));
        assertFalse(Urgency.isValidUrgency("12"));
        assertFalse(Urgency.isValidUrgency("4"));
        assertFalse(Urgency.isValidUrgency(" "));
    }

    @Test
    public void isValidUrgencyString_failure() {
        assertFalse(Urgency.isValidUrgencyString("medium"));
        assertFalse(Urgency.isValidUrgencyString("12"));
        assertFalse(Urgency.isValidUrgencyString("4"));
        assertFalse(Urgency.isValidUrgencyString(" "));
    }
}
