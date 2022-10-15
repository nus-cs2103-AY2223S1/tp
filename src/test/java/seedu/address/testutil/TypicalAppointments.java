package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.Appointment;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment APPOINTMENT_BENSON =
            new Appointment("Sore Throat", "2019-12-10 16:30", "", false);
    public static final Appointment APPOINTMENT_CARL =
            new Appointment("Cough", "2010-12-31 23:45", "", true);
    public static final Appointment SECOND_APPOINTMENT_CARL =
            new Appointment("Sore Throat", "2019-12-10 16:30", "", true);

    public static List<Appointment> getTypicalAppointments() {
        APPOINTMENT_BENSON.setPatient(BENSON);
        APPOINTMENT_CARL.setPatient(CARL);
        SECOND_APPOINTMENT_CARL.setPatient(CARL);
        return new ArrayList<>(Arrays.asList(APPOINTMENT_CARL, APPOINTMENT_BENSON, SECOND_APPOINTMENT_CARL));
    }
}
