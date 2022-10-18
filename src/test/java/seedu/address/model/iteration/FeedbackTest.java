package seedu.address.model.iteration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class FeedbackTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Feedback(null));
    }

    @Test
    public void constructor_anyString_success() {
        assertDoesNotThrow(() -> new Feedback(""));
        assertDoesNotThrow(() -> new Feedback("   "));
        assertDoesNotThrow(() -> new Feedback("non-empty string"));
    }

    @Test
    public void equals() {
        String feedback1String = "feedback 1";
        Feedback feedback1 = new Feedback(feedback1String);
        Feedback feedback2 = new Feedback("feedback 2");

        // same object -> returns true
        assertTrue(feedback1.equals(feedback1));

        // different type -> returns false
        assertFalse(feedback1.equals(1));

        // null -> returns false
        assertFalse(feedback1.equals(null));

        // same values (copy) -> returns true
        assertTrue(feedback1.equals(new Feedback(feedback1String)));

        // different feedback -> returns false
        assertFalse(feedback1.equals(feedback2));
    }
}
