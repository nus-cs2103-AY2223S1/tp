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
    public void constructor_invalidTutorialDetail_throwsIllegalArgumentException() {
        String invalidTutorialDetail = "";
        assertThrows(IllegalArgumentException.class, () -> new TutorialDetails(invalidTutorialDetail));
    }

    @Test
    public void isValidTutorialDetailsDescription() {
        // null tutorial details
        assertThrows(NullPointerException.class, () -> TutorialDetails.areValidTutorialDetails(null));

        // invalid tutorial details
        assertFalse(TutorialDetails.areValidTutorialDetails(" ")); // Empty string

        // valid tutorial details
        assertTrue(TutorialDetails.areValidTutorialDetails("Monday 1400")); // Spaces allowed
        assertTrue(TutorialDetails.areValidTutorialDetails("Tuesday"));
        assertTrue(TutorialDetails.areValidTutorialDetails("Wednesday Friday 1800")); // Multiple words
        assertTrue(TutorialDetails.areValidTutorialDetails("Tuesday, Thursday, Friday")); // Non-alphanumerics allowed
    }
}


