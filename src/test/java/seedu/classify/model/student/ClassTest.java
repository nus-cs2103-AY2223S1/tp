package seedu.classify.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.classify.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ClassTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Class(null));
    }

    @Test
    public void constructor_invalidClass_throwsIllegalArgumentException() {
        String invalidClass = "";
        assertThrows(IllegalArgumentException.class, () -> new Class(invalidClass));
    }

    @Test
    public void isValidClass() {
        // null address
        assertThrows(NullPointerException.class, () -> Class.isValidClassName(null));

        // invalid addresses
        assertFalse(Class.isValidClassName("")); // empty string
        assertFalse(Class.isValidClassName(" ")); // spaces only

        // valid addresses
        assertTrue(Class.isValidClassName("A")); // one character
        assertTrue(Class.isValidClassName("20A")); // character and number
        assertTrue(Class.isValidClassName("Loyalty 9")); // word and number
    }
}
