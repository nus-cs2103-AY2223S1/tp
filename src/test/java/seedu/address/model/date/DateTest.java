package seedu.address.model.date;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new Date(invalidDate));
    }

    @Test
    public void toAge_olderThan20_success() {
        Date firstDate = new Date("11/05/2000");
        assertTrue(firstDate.toAge() > 20);
    }

    @Test
    public void toAge_olderThan40_success() {
        Date firstDate = new Date("11/05/1980");
        assertTrue(firstDate.toAge() > 40);
    }

    @Test
    public void isAfterCurrentDate_nextDay_fail() {
        Date pastDate = new Date("11/05/2021");
        assertFalse(pastDate.isAfterCurrentDate("12/05/2022"));
    }

    @Test
    public void isAfterCurrentDate_previousDay_success() {
        Date pastDate = new Date("11/05/2021");
        assertFalse(pastDate.isAfterCurrentDate("10/05/2022"));
    }

    @Test
    public void equals_sameDate_success() {
        Date dateA = new Date("11/05/2021");
        Date dateB = new Date("11/05/2021");
        assertTrue(dateA.equals(dateB));
    }

    @Test
    public void equals_differentDate_fail() {
        Date dateA = new Date("11/05/2021");
        Date dateB = new Date("12/05/2021");
        assertFalse(dateA.equals(dateB));
    }

    @Test
    public void isValidDateFormatAndValue() {

        // null DateOfBirth
        assertThrows(NullPointerException.class, () -> Date.isValidDate(null));

        // blank DateOfBirth
        assertFalse(Date.isValidDate("")); // empty string
        assertFalse(Date.isValidDate(" ")); // spaces only
        assertFalse(Date.isValidDate("      ")); // many spaces
        assertFalse(Date.isValidDate("//"));

        // invalid DateOfBirth
        assertFalse(Date.isValidDate("1.1.2000"));
        assertFalse(Date.isValidDate("12/23/2000"));
        assertFalse(Date.isValidDate("12 Mar 1993"));
        assertFalse(Date.isValidDate("00/01/2000"));
        assertFalse(Date.isValidDate("01/00/2000"));
        assertFalse(Date.isValidDate("00/00/2000"));

        // Year 0000 corner case
        assertFalse(Date.isValidDate("01/01/0000"));
        assertFalse(Date.isValidDate("01/01/000"));
        assertFalse(Date.isValidDate("01/01/00"));
        assertFalse(Date.isValidDate("01/01/0"));

        // not supposed to have alphabetical or other characters
        assertFalse(Date.isValidDate("01/01/A000"));
        assertFalse(Date.isValidDate("01/0A/2000"));
        assertFalse(Date.isValidDate("0A/01/2000"));
        assertFalse(Date.isValidDate("0$/01/2000"));
        assertFalse(Date.isValidDate("01/0%/2000"));
        assertFalse(Date.isValidDate("01/01/200#"));

        // wrong format
        assertFalse(Date.isValidDate("01/01/0"));
        assertFalse(Date.isValidDate("01/01/00"));
        assertFalse(Date.isValidDate("01/01/000"));
        assertFalse(Date.isValidDate("01/0/2000"));
        assertFalse(Date.isValidDate("0/01/2000"));
        assertFalse(Date.isValidDate("A/B/C")); //wrong format and alphabetical characters

        // valid DateOfBirth
        assertTrue(Date.isValidDate("01/01/2022")); //UG example
        assertTrue(Date.isValidDate("20/03/2002")); //UG example
        assertTrue(Date.isValidDate("14/12/1998")); //UG example
        assertTrue(Date.isValidDate("03/04/1998")); //UG example
        assertTrue(Date.isValidDate("20/04/2022")); //UG example
        assertTrue(Date.isValidDate("10/10/2022")); //UG example
        assertTrue(Date.isValidDate("23/12/2000"));
        assertTrue(Date.isValidDate("02/11/1900"));
        assertTrue(Date.isValidDate("11/11/1111"));
    }
}
