package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class OfficeHourTest {

    @Test
    void isValidOfficeHour() {
        // invalid office hour
        assertFalse(OfficeHour.isValidOfficeHour("MONDAY 12:00 PM - 01:00 PM")); // no comma
        assertFalse(OfficeHour.isValidOfficeHour("MONDAY, 12:00 PM - 13:00 PM")); // hour exceeds 12
        assertFalse(OfficeHour.isValidOfficeHour("MONDAY, 25:00 PM - 01:00 PM")); // hour exceeds 12

        // valid office hour
        assertTrue(OfficeHour.isValidOfficeHour("MONDAY, 12:00 PM - 01:00 PM"));
        assertTrue(OfficeHour.isValidOfficeHour("TUESDAY, 02:00 AM - 03:00 PM"));
        assertTrue(OfficeHour.isValidOfficeHour("WEDNESDAY, 04:00 AM - 03:00 PM"));
        assertTrue(OfficeHour.isValidOfficeHour("THURSDAY, 03:00 AM - 04:00 PM"));
        assertTrue(OfficeHour.isValidOfficeHour("FRIDAY, 05:00 AM - 06:00 PM"));
    }

    @Test
    void isValidOfficeHourInstruction() {
        // invalid office hour instruction
        assertFalse(OfficeHour.isValidOfficeHourInstruction("6-12:00-2")); // day specified not within 0 <= i <= 5
        assertFalse(OfficeHour.isValidOfficeHourInstruction("0-12:00-2")); // day specified not within 0 <= i <= 5
        assertFalse(OfficeHour.isValidOfficeHourInstruction("6-12:00-10")); // office hour duration exceeds 9 hours
    }
}
