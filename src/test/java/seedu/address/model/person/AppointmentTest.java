package seedu.address.model.person;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateTimeParser;

public class AppointmentTest {

    /**
     * A stub class to check the String representation
     * of the DateTime and to check equality.
     */
    private static class DateTimeStub extends DateTime {
        DateTimeStub() {
            super(DateTimeParser.parseLocalDateTimeFromString("1-Apr-2023 12:30 PM"));
        }

        @Override
        public String toString() {
            return "1-Apr-2023 12:30 PM";
        }
    }

    /**
     * A stub class to check the String representation
     * of the DateTime and to check equality.
     */
    private static class LocationStub extends Location {
        LocationStub() {
            super("NUS TechnoEdge");
        }

        @Override
        public String toString() {
            return "NUS TechnoEdge";
        }
    }
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Appointment(null, null));
    }

    @Test
    public void versionToString_validVersion_correctStringRepresentation() {
        Appointment newAppointment = new Appointment(new DateTimeStub(), new LocationStub());
        Assertions.assertEquals("1-Apr-2023 12:30 PM, NUS TechnoEdge", newAppointment.toString());
    }

    @Test
    public void equals_success() {
        Appointment firstAppointment = new Appointment(new DateTimeStub(), new LocationStub());
        Appointment secondAppointment = new Appointment(new DateTimeStub(), new LocationStub());

        Assertions.assertEquals(firstAppointment, firstAppointment);
        Assertions.assertNotEquals(firstAppointment, null);
        Assertions.assertEquals(firstAppointment, secondAppointment);
    }
}
