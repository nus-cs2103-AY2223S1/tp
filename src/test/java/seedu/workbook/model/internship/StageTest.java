package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StageTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Stage(null));
    }

    @Test
    public void constructor_invalidName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new Stage(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> Stage.isValidStage(null));

        // invalid name
        assertFalse(Stage.isValidStage("")); // empty string
        assertFalse(Stage.isValidStage(" ")); // spaces only
        assertFalse(Stage.isValidStage("^")); // only non-alphanumeric characters
        assertFalse(Stage.isValidStage("Interview*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(Stage.isValidStage("Phone Interview")); // alphabets only
        assertTrue(Stage.isValidStage("12345")); // numbers only
        assertTrue(Stage.isValidStage("3rd Technical Interview")); // alphanumeric characters
        assertTrue(Stage.isValidStage("BEHAVIOURAL INTERVIEW")); // with capital letters
        assertTrue(Stage.isValidStage("Coding with the CTO of the company intense interview")); // long names
    }
}
