package seedu.address.model.tutorial;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TutorialNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TutorialName(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new TutorialName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> TutorialName.isValidName(null));

        // invalid name
        assertFalse(TutorialName.isValidName("")); // empty string
        assertFalse(TutorialName.isValidName(" ")); // spaces only
        assertTrue(TutorialName.isValidName("^")); // only non-alphanumeric characters
        assertTrue(TutorialName.isValidName("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(TutorialName.isValidName("peter jack")); // alphabets only
        assertTrue(TutorialName.isValidName("12345")); // numbers only
        assertTrue(TutorialName.isValidName("peter the 2nd")); // alphanumeric characters
        assertTrue(TutorialName.isValidName("Capital Tan")); // with capital letters
        assertTrue(TutorialName.isValidName("David Roger Jackson Ray Jr 2nd")); // long names
    }
}
