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
        assertTrue(ExamDate.isValidDate("31-12-2025"));
        assertTrue(ExamDate.isValidDate("01-01-2025"));
        assertTrue(ExamDate.isValidDate("28-02-2025"));

        //Past date
        assertFalse(ExamDate.isNotAPastDate("28-10-2022"));
        assertFalse(ExamDate.isNotAPastDate("13-02-1999"));

        //Not in DD-MM-YYYY format
        assertFalse(ExamDate.isValidDateFormat("2025-08-20"));
        assertFalse(ExamDate.isValidDateFormat("20-9-2025"));
        assertFalse(ExamDate.isValidDateFormat("020-08-2025"));
        assertFalse(ExamDate.isValidDateFormat("ab-bc-asad"));
        assertFalse(ExamDate.isValidDateFormat("20-08 -2024"));
        assertFalse(ExamDate.isValidDateFormat("")); // empty string
        assertFalse(ExamDate.isValidDateFormat(" ")); // spaces only


        assertFalse(ExamDate.isValidDate("29-02-2023"));
        assertFalse(ExamDate.isValidDate("31-11-2024"));
    }
}
