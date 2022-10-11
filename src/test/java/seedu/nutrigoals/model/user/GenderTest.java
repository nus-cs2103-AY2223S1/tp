package seedu.nutrigoals.model.user;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.nutrigoals.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;



public class GenderTest {
    @Test
    public void constructor_nullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidThrowsIllegalArgumentException() {
        String invalidGender = " ";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("m"));
        assertTrue(Gender.isValidGender("F"));
        assertTrue(Gender.isValidGender("f"));
        assertFalse(Gender.isValidGender("Male"));
        assertFalse(Gender.isValidGender("female"));
        assertFalse(Gender.isValidGender("m1"));
        assertFalse(Gender.isValidGender("f3"));
    }

    @Test
    public void equals() {
        Gender male = new Gender("m");
        Gender female = new Gender("f");
        Gender male2 = new Gender("M");

        assertTrue(male.equals(male2));
        assertTrue(male.equals(male));
        assertFalse(male.equals("M"));
        assertFalse(male.equals(female));
        assertFalse(male.equals(null));
    }
}
