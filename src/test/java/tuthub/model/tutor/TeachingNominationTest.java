package tuthub.model.tutor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TeachingNominationTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TeachingNomination(null));
    }

    @Test
    public void constructor_invalidTeachingNomination_throwsIllegalArgumentException() {
        String invalidTeachingNomination = "-1";
        assertThrows(IllegalArgumentException.class, () -> new TeachingNomination(invalidTeachingNomination));
    }

    @Test
    public void isValidTeachingNomination() {
        // null teachingNomination
        assertThrows(NullPointerException.class, () -> TeachingNomination.isValidTeachingNomination(null));

        // blank teachingNomination
        assertFalse(TeachingNomination.isValidTeachingNomination("")); // empty string
        assertFalse(TeachingNomination.isValidTeachingNomination(" ")); // spaces only

        // invalid teachingNomination
        assertFalse(TeachingNomination.isValidTeachingNomination("-1")); // less than 0
        assertFalse(TeachingNomination.isValidTeachingNomination("1.1")); // with decimal

        // valid teachingNomination
        assertTrue(TeachingNomination.isValidTeachingNomination("0"));
        assertTrue(TeachingNomination.isValidTeachingNomination("1"));
        assertTrue(TeachingNomination.isValidTeachingNomination("100"));
        assertTrue(TeachingNomination.isValidTeachingNomination("200"));
    }
}

