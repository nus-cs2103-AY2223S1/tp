package seedu.address.model.module;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialDetailsTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialDetails(null));
    }

    @Test
    public void constructor_invalidTutorialDetails_throwsIllegalArgumentException() {
        String invalidTutorialDetails = "";
        assertThrows(IllegalArgumentException.class, () -> new TutorialDetails(invalidTutorialDetails));
    }

    @Test
    public void isValidTutorialDetails() {
        // null tutorial details
        assertThrows(NullPointerException.class, () -> TutorialDetails.areValidTutorialDetails(null));

        // invalid tutorial details
        assertFalse(TutorialDetails.areValidTutorialDetails("")); // empty string
        assertFalse(TutorialDetails.areValidTutorialDetails(" ")); // spaces only

        // valid tutorial details
        assertTrue(TutorialDetails.areValidTutorialDetails("Tuesday 1500"));
        assertTrue(TutorialDetails.areValidTutorialDetails("-")); // one character
        assertTrue(TutorialDetails.areValidTutorialDetails("No tutorial"));
    }
}
