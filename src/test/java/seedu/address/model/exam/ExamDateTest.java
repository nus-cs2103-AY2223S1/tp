package seedu.address.model.exam;

import org.junit.jupiter.api.Test;
import seedu.address.model.person.Address;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

public class ExamDateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ExamDate(null));
    }

    @Test
    public void constructor_invalidExamDate_throwsIllegalArgumentException() {
        String invalidExamDate = "";
        assertThrows(IllegalArgumentException.class, () -> new ExamDate(invalidExamDate));
    }

    @Test
    public void isValidExamDate() {
        // null address
        assertThrows(NullPointerException.class, () -> ExamDate.isValidDate(null));

        // invalid addresses
        assertFalse(ExamDate.isValidDate("")); // empty string
        assertFalse(ExamDate.isValidDate(" ")); // spaces only

        // valid addresses
        //should the date include existing date may be outdated
        assertTrue(ExamDate.isValidDate("20-10-2025"));
        assertTrue(ExamDate.isValidDate("20-01-2025")); // one character
       // assertTrue(ExamDate.isValidDate("Leng Inc; 1234 Market St; San Francisco CA 2349879; USA")); // long address
    }
}
