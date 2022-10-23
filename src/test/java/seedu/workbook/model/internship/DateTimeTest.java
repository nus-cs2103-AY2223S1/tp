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
        assertThrows(NullPointerException.class, () -> DateTime.isValidDate(null));

        // blank DateTime
        assertFalse(DateTime.isValidDate(" ")); // spaces only

        // missing parts to DateTime input
        assertFalse(DateTime.isValidDate("20-Nov-2022")); // missing time
        assertFalse(DateTime.isValidDate("20 Nov 2022")); // missing hyphen
        assertFalse(DateTime.isValidDate("18:00")); // no date provided only time

        // invalid parts
        assertFalse(DateTime.isValidDate("20 18:00")); // missing full date
        assertFalse(DateTime.isValidDate("20-Oct-2022 18-00")); // hyphen when putting in time
        assertFalse(DateTime.isValidDate("20Oct2022 18:00")); // missing hyphens in the date
        assertFalse(DateTime.isValidDate("20-Ja-2022 17:00")); // only two letters in month
        assertFalse(DateTime.isValidDate(" peterjack@example.com")); // leading space
        assertFalse(DateTime.isValidDate("peterjack@example.com ")); // trailing space
        assertFalse(DateTime.isValidDate("20--Oct-2022 19:00")); // double '-' symbol
        assertFalse(DateTime.isValidDate("20--Oct-2022-19:00")); // '-' symbol used in the wrong part
        assertFalse(DateTime.isValidDate("-20-Oct-2022 19:00")); // date starts with a hyphen
        assertFalse(DateTime.isValidDate("-20-Oct-2022 19:00-")); // DateTime ends with a hyphen
        assertFalse(DateTime.isValidDate("20:Oct:2022 19:00")); // Date uses ':' as separator instead of '-'
        assertFalse(DateTime.isValidDate("2-Nov-20 19:00")); // year part of the date only has two chars


        // valid email
        assertTrue(DateTime.isValidDate("02-Feb-2022 18:00")); // follows the correct dd-mmm-yyyy hh:mm format
    }
}

