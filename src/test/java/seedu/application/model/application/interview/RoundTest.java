package seedu.application.model.application.interview;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.application.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RoundTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Round(null));
    }

    @Test
    public void constructor_invalidRound_throwsIllegalArgumentException() {
        String invalidRound = "";
        assertThrows(IllegalArgumentException.class, () -> new Round(invalidRound));
    }

    @Test
    public void isValidRound() {
        // null round
        assertThrows(NullPointerException.class, () -> Round.isValidRound(null));

        // invalid round
        assertFalse(Round.isValidRound("")); // empty string
        assertFalse(Round.isValidRound(" ")); // spaces only
        assertFalse(Round.isValidRound("^")); // only non-alphanumeric characters
        assertFalse(Round.isValidRound("technical*")); // contains non-alphanumeric characters

        // valid round
        assertTrue(Round.isValidRound("assessment")); // alphabets only
        assertTrue(Round.isValidRound("12345")); // numbers only
        assertTrue(Round.isValidRound("technical interview 1")); // alphanumeric characters
        assertTrue(Round.isValidRound("HR Interview")); // with capital letters
        assertTrue(Round.isValidRound("Technical Interview with their Manager and HRs")); // long round name
    }

    @Test
    public void round() {
        assertEquals(new Round("Technical interview 1").toString(), "Technical interview 1");
    }

}
