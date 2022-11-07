package seedu.workbook.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.workbook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }
    @Test
    public void isValidDateTime() {
        // null DateTime
        assertThrows(NullPointerException.class, () -> DateTime.isValidDateTime(null));

        // blank DateTime
        assertFalse(DateTime.isValidDateTime(" ")); // spaces only

        // missing parts to DateTime input
        assertFalse(DateTime.isValidDateTime("20-Nov-2022")); // missing time
        assertFalse(DateTime.isValidDateTime("20 Nov 2022")); // missing hyphen
        assertFalse(DateTime.isValidDateTime("18:00")); // no date provided only time

        // invalid parts
        assertFalse(DateTime.isValidDateTime("20 18:00")); // missing full date
        assertFalse(DateTime.isValidDateTime("20-Oct-2022 18-00")); // hyphen when putting in time
        assertFalse(DateTime.isValidDateTime("20Oct2022 18:00")); // missing hyphens in the date
        assertFalse(DateTime.isValidDateTime("20-Ja-2022 17:00")); // only two letters in month
        assertFalse(DateTime.isValidDateTime("20--Oct-2022 19:00")); // double '-' symbol
        assertFalse(DateTime.isValidDateTime("20--Oct-2022-19:00")); // '-' symbol used in the wrong part
        assertFalse(DateTime.isValidDateTime("-20-Oct-2022 19:00")); // date starts with a hyphen
        assertFalse(DateTime.isValidDateTime("-20-Oct-2022 19:00-")); // DateTime ends with a hyphen
        assertFalse(DateTime.isValidDateTime("20:Oct:2022 19:00")); // Date uses ':' as separator instead of '-'
        assertFalse(DateTime.isValidDateTime("2-Nov-20 19:00")); // year part of the date only has two chars


        // valid email
        assertTrue(DateTime.isValidDateTime("02-Feb-2022 18:00")); // follows the correct dd-mmm-yyyy hh:mm format
    }
}

