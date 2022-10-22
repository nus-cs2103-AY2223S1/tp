package seedu.address.model.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

public class FormatDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FormatDate(null));
    }

    @Test
    public void constructor_invalidFormatDate_throwsIllegalArgumentException() {
        String invalidFormatDate = "";
        assertThrows(IllegalArgumentException.class, () -> new FormatDate(invalidFormatDate));
    }

    @Test
    public void correctDateFormat_validFormatDate_correctAlternativeDateFormat() {
        String validInputDate = "Dec 12 2012";
        String expectedOutputDate = "2012-12-12";
        assertEquals(expectedOutputDate, FormatDate.correctDateFormat(validInputDate));
    }

    @Test
    public void correctDateFormat_invalidFormatDate_runtimeException() {
        String invalidInputDate = "2012-12-12";
        assertThrows(RuntimeException.class, () -> FormatDate.correctDateFormat(invalidInputDate));
    }
}
