package seedu.address.model.exam;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Name;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ExamDescriptionTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExamDescription(null));
    }

    @Test
    public void constructor_invalidExamDescription_throwsIllegalArgumentException() {
        String invalidExamDescription = "";
        assertThrows(IllegalArgumentException.class, () -> new ExamDescription(invalidExamDescription));
    }

    @Test
    public void isValidExamDescription() {
        // null name
        assertThrows(NullPointerException.class, () -> Name.isValidName(null));

        // invalid name
        assertFalse(ExamDescription.isValidDescription(""));// empty string
        //assertFalse(ExamDescription.isValidDescription(" ")); // spaces only
//        assertTrue(ExamDescription.isValidDescription("^")); // only non-alphanumeric characters
//        assertTrue(ExamDescription.isValidDescription("peter*")); // contains non-alphanumeric characters

        // valid name
        assertTrue(ExamDescription.isValidDescription("finals")); // alphabets only
        assertTrue(ExamDescription.isValidDescription("  finals  ")); // alphab
        assertTrue(ExamDescription.isValidDescription("Final paper"));
        assertTrue(ExamDescription.isValidDescription("Final paper 1"));
        assertTrue(ExamDescription.isValidDescription("MIDTERMS"));
        assertTrue(ExamDescription.isValidDescription("Final paper testing on topic 1 2 3 4 5"));


    }
}
