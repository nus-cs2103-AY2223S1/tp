package jeryl.fyp.model.student;

import static jeryl.fyp.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DeadlineNameTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineName(null));
    }

    @Test
    public void constructor_invalidDeadlineName_throwsIllegalArgumentException() {
        String invalidName = "";
        assertThrows(IllegalArgumentException.class, () -> new DeadlineName(invalidName));
    }

    @Test
    public void isValidName() {
        // null name
        assertThrows(NullPointerException.class, () -> DeadlineName.isValidDeadlineName(null));

        // invalid name
        assertFalse(DeadlineName.isValidDeadlineName("")); // empty string
        assertFalse(DeadlineName.isValidDeadlineName(" ")); // spaces only
        assertFalse(DeadlineName.isValidDeadlineName("   ")); // multiple spaces
        assertFalse(DeadlineName.isValidDeadlineName("^")); // only non-alphanumeric characters
        assertFalse(DeadlineName.isValidDeadlineName("+task+")); // starting with non-alphanumeric characters
        assertFalse(DeadlineName.isValidDeadlineName("Machine Learn project -- fix bugs"));

        // valid name
        assertTrue(ProjectName.isValidProjectName("some random alphabets")); // alphabets only
        assertTrue(ProjectName.isValidProjectName("12345")); // numbers only
        assertTrue(ProjectName.isValidProjectName("some random alphabets 12345")); // alphanumeric characters
        assertTrue(ProjectName.isValidProjectName("Submit TP code")); // with capital letters
        // long name
        assertTrue(ProjectName.isValidProjectName("Final report of Generalized Autoencoder for "
                + "dimensionality reduction"));
    }
}
