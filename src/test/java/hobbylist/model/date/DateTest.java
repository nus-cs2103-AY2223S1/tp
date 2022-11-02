package hobbylist.model.date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void equals() {
        Date firstDate = new Date("2020-04-12");
        Date secondDate = new Date("1900-01-01");

        // null -> returns false
        assertFalse(firstDate.equals(null));

        // wrong type -> return false
        assertFalse(firstDate.equals("haha"));

        // same object -> return true
        assertTrue(firstDate.equals(firstDate));

        // same value -> return true
        Date firstDateCopy = new Date("2020-04-12");
        assertTrue(firstDate.equals(firstDateCopy));

        // different value -> return false
        assertFalse(firstDate.equals(secondDate));
    }

    @Test
    public void yearMonthDescription() {
        assertEquals(new Date("2020-10-12").yearMonthDescription(), "2020-10");
    }

    @Test
    public void yearDescription() {
        assertEquals(new Date("2020-10-12").yearDescription(), "2020");
    }

    @Test
    public void toViewString() {
        assertEquals(new Date("2020-10-12").toViewString(), "[Oct 12 2020]");
    }

    @Test
    public void toDisplayString() {
        assertEquals(new Date("2020-10-12").toDisplayedString(), "Oct 12 2020");
    }

    @Test
    public void tostring() {
        assertEquals(new Date("2020-10-12").toString(), "2020-10-12");
    }

    @Test
    public void hashcode() {
        assertEquals(new Date("2020-10-12").hashCode(), LocalDate.parse("2020-10-12").hashCode());
    }

    @Test
    public void getDate() {
        assertEquals(new Date("2020-10-12").getDate(), LocalDate.parse("2020-10-12"));
    }

    @Test
    public void getOriginalString() {
        assertEquals(new Date("2020-10-12").getOriginalString(), "2020-10-12");
    }

    @Test
    public void isValidDateString_invalidDate_returnsFalse() {
        assertFalse(Date.isValidDateString("test"));
    }

    @Test
    public void isValidDateString_date0000_returnsFalse() {
        assertFalse(Date.isValidDateString("0000-10-12"));
    }

    @Test
    public void isValidDateString_validDate_returnsTrue() {
        assertTrue(Date.isValidDateString("1999-10-12"));
    }
}
