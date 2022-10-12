package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

public class ClassTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Class(null, null, null, null));
    }
    @Test
    public void isValidClassString() {
        assertTrue(Class.isValidClassString("2022-05-05 1200-1300"));
        assertTrue(Class.isValidClassString("2022-12-05 1330-1400"));
    }

    @Test
    public void isInvalidClassString() {
        assertFalse(Class.isValidClassString("2022-05-05 1200 1300"));
        assertFalse(Class.isValidClassString(""));
    }

    @Test
    public void toStringTest() {
        LocalDate validDate = LocalDate.of(2022, 10, 11);
        LocalTime validStartTime = LocalTime.of(0, 0);
        LocalTime validEndTime = LocalTime.of(23, 59);
        String validClassDateTimeString = "2022-10-11 0000-2359";
        Class validClass = new Class(validDate, validStartTime, validEndTime, validClassDateTimeString);
        String expectedValidToStringResult = "11 Oct 2022 0AM-11.59PM";
        assertTrue(validClass.toString().equals(expectedValidToStringResult));

        Class emptyClass = new Class();
        assertTrue(emptyClass.toString().equals(""));
    }

    @Test
    public void toTimeStringTest() {
        LocalDate validDate = LocalDate.of(2022, 10, 11);
        LocalTime validStartTime = LocalTime.of(0, 0);
        LocalTime validEndTime = LocalTime.of(23, 59);
        String validClassDateTimeString = "2022-10-11 0000-2359";
        Class validClass = new Class(validDate, validStartTime, validEndTime, validClassDateTimeString);
        String expectedTimeString = "0AM-11.59PM";
        assertTrue(validClass.toTimeString().equals(expectedTimeString));

        Class emptyClass = new Class();
        assertTrue(emptyClass.toTimeString().equals(""));
    }

}
