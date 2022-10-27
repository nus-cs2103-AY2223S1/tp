package seedu.watson.model.person.subject;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.watson.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AssessmentTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
            new Assessment(null, 0.0, 0.0,
                0.0, 0.0));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () ->
            new Assessment(invalidName, 10.0, 10.0,
                10.0, 5.0));
    }

    @Test
    public void isValidAssessmentName() {
        // null name
        assertThrows(NullPointerException.class, () -> Assessment.isValidAssessment(null));

        // invalid name
        assertFalse(Assessment.isValidAssessment("")); // empty string
        assertFalse(Assessment.isValidAssessment(" ")); // spaces only
        assertFalse(Assessment.isValidAssessment("^")); // only non-alphanumeric characters
        assertFalse(Assessment.isValidAssessment("CA1*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Assessment.isValidAssessment("continuous assessment")); // alphabets only
        assertTrue(Assessment.isValidAssessment("1")); // numbers only
        assertTrue(Assessment.isValidAssessment("CA1")); // alphanumeric characters
        assertTrue(Assessment.isValidAssessment("Continuous Assessment")); // with capital letters
        assertTrue(Assessment.isValidAssessment("Final Year Examination 2022")); // long names
    }
}
