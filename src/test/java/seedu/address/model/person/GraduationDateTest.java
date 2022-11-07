package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class GraduationDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new GraduationDate(null));
    }

    @Test
    public void constructor_invalidGraduationDateType_throwsIllegalArgumentException() {
        String invalidGraduationDateType = "";
        assertThrows(IllegalArgumentException.class, () -> new GraduationDate(invalidGraduationDateType));
    }

    @Test
    public void isValidGraduationDate() {
        // null graduation date
        assertThrows(NullPointerException.class, () -> GraduationDate.isValidGraduationDate(null));

        // blank graduation date
        assertFalse(GraduationDate.isValidGraduationDate("")); // empty string
        assertFalse(GraduationDate.isValidGraduationDate("   ")); // only spaces
        assertFalse(GraduationDate.isValidGraduationDate("!")); // non-alphanumeric character
        assertFalse(GraduationDate.isValidGraduationDate("a")); // alphabet

        // invalid month
        assertFalse(GraduationDate.isValidGraduationDate("a-2024")); // month is alphabetical
        assertFalse(GraduationDate.isValidGraduationDate("^-2024")); // month is non-numeric
        assertFalse(GraduationDate.isValidGraduationDate("1-2024")); // month is 1 digit
        assertFalse(GraduationDate.isValidGraduationDate("00-2024")); // month is zero
        assertFalse(GraduationDate.isValidGraduationDate("13-2024")); // month doesn't exist
        assertFalse(GraduationDate.isValidGraduationDate("20-2024")); // month doesn't exist
        assertFalse(GraduationDate.isValidGraduationDate("99-2024")); // month doesn't exist
        assertFalse(GraduationDate.isValidGraduationDate("111-2024")); // month is 3 digits

        // invalid year
        assertFalse(GraduationDate.isValidGraduationDate("01-a")); // month is alphabetical
        assertFalse(GraduationDate.isValidGraduationDate("01-^")); // month is non-numeric
        assertFalse(GraduationDate.isValidGraduationDate("01-1")); // year is 1 digit
        assertFalse(GraduationDate.isValidGraduationDate("01-11")); // year is 2 digit
        assertFalse(GraduationDate.isValidGraduationDate("01-111")); // year is 3 digit
        assertFalse(GraduationDate.isValidGraduationDate("01-11111")); // year is 5 digit

        // invalid separator
        assertFalse(GraduationDate.isValidGraduationDate("012024")); // no separator
        assertFalse(GraduationDate.isValidGraduationDate("01 2024")); // space separator
        assertFalse(GraduationDate.isValidGraduationDate("01/2024")); // '/' separator
        assertFalse(GraduationDate.isValidGraduationDate("01--2024")); // -- extra '-' in separator

        // no substring
        assertFalse(GraduationDate.isValidGraduationDate("001-20244")); // duplicate edges
        assertFalse(GraduationDate.isValidGraduationDate("[01-2024]")); // square bracket
        assertFalse(GraduationDate.isValidGraduationDate("/01-2024/")); // '/' regex
        assertFalse(GraduationDate.isValidGraduationDate("/////01-2024")); // extended start
        assertFalse(GraduationDate.isValidGraduationDate("01-2024//////")); // extended end

        assertFalse(GraduationDate.isValidGraduationDate("o1-2o24")); // mix alphanumeric

        // valid graduation date
        assertTrue(GraduationDate.isValidGraduationDate("01-2024")); // month that leads with '0'
        assertTrue(GraduationDate.isValidGraduationDate("12-2024")); // 2 digit month
        assertTrue(GraduationDate.isValidGraduationDate("01-1900")); // non 20th century
        assertTrue(GraduationDate.isValidGraduationDate("01-0000")); // 0th year
        assertTrue(GraduationDate.isValidGraduationDate("01-3000")); // next century
    }
}
