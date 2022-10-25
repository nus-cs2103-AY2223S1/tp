package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Tutorial(null));
    }

    @Test
    public void constructor_invalidTutorial_throwsIllegalArgumentException() {
        String invalidTutorial = "A";
        assertThrows(IllegalArgumentException.class, () -> new Tutorial(invalidTutorial));
    }

    @Test
    public void isValidTutorial() {
        // null tutorial
        assertThrows(NullPointerException.class, () -> Tutorial.isValidTutorial(null));

        // invalid tutorial group
        assertFalse(Tutorial.isValidTutorial("")); // empty string
        assertFalse(Tutorial.isValidTutorial("A")); // only one character

        // valid tutorial group
        assertTrue(Tutorial.isValidTutorial("T08")); // 3 digits
    }
}
