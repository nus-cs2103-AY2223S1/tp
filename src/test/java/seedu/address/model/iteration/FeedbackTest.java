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
        String someFeedbackString = "feedback 1";
        Feedback someFeedback = new Feedback(someFeedbackString);
        Feedback otherFeedback = new Feedback("feedback 2");

        // same object -> returns true
        assertTrue(someFeedback.equals(someFeedback));

        // different type -> returns false
        assertFalse(someFeedback.equals(1));

        // null -> returns false
        assertFalse(someFeedback.equals(null));

        // same values (copy) -> returns true
        assertTrue(someFeedback.equals(new Feedback(someFeedbackString)));

        // different feedback -> returns false
        assertFalse(someFeedback.equals(otherFeedback));
    }
}
