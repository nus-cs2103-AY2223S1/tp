package seedu.address.testutil;

import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.GEORGE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.model.person.Appointment;
import seedu.address.model.tag.Tag;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalAppointments {
    public static final Appointment APPOINTMENT_BENSON =
            new Appointment("Sore Throat", "2019-12-10 16:30", "", false);
    public static final Appointment APPOINTMENT_CARL =
            new Appointment("Cough", "2010-12-31 23:45", "",
                    Set.of(Tag.THROAT), true);
    public static final Appointment SECOND_APPOINTMENT_CARL =
            new Appointment("Sore Throat", "2019-12-10 16:30", "1Y",
                    Set.of(Tag.EAR), false);

    public static final Appointment APPOINTMENT_DANIEL =
            new Appointment("Consultation", "2013-12-23 21:43", "",
                    Set.of(), true);
    public static final Appointment APPOINTMENT_ELLE =
            new Appointment("Hearing Impairment and Nosebleed", "2016-02-21 20:36", "",
                    Set.of(Tag.EAR, Tag.NOSE), false);
    public static final Appointment APPOINTMENT_GEORGE =
            new Appointment("Rhinitis", "2020-04-31 03:46", "",
                    Set.of(Tag.EAR, Tag.NOSE), true);
    public static final Appointment SECOND_APPOINTMENT_GEORGE =
            new Appointment("Hearing Impairment", "2010-12-31 23:46", "",
                    Set.of(Tag.EAR), false);


    public static List<Appointment> getTypicalAppointments() {
        APPOINTMENT_BENSON.setPatient(BENSON);
        APPOINTMENT_CARL.setPatient(CARL);
        SECOND_APPOINTMENT_CARL.setPatient(CARL);
        return new ArrayList<>(Arrays.asList(new AppointmentBuilder(APPOINTMENT_CARL).build(),
                new AppointmentBuilder(APPOINTMENT_BENSON).build(),
                new AppointmentBuilder(SECOND_APPOINTMENT_CARL).build()));
    }

    public static void setAll() {
        APPOINTMENT_BENSON.setPatient(BENSON);
        APPOINTMENT_CARL.setPatient(CARL);
        SECOND_APPOINTMENT_CARL.setPatient(CARL);
        APPOINTMENT_DANIEL.setPatient(DANIEL);
        APPOINTMENT_ELLE.setPatient(ELLE);
        APPOINTMENT_GEORGE.setPatient(GEORGE);
        SECOND_APPOINTMENT_GEORGE.setPatient(GEORGE);
    }

    public static List<Appointment> getDefaultAppointments() {
        setAll();
        return new ArrayList<>(Arrays.asList(
                new AppointmentBuilder(APPOINTMENT_CARL).withTags("throat").build(),
                new AppointmentBuilder(SECOND_APPOINTMENT_GEORGE).build(),
                new AppointmentBuilder(APPOINTMENT_DANIEL).build(),
                new AppointmentBuilder(APPOINTMENT_ELLE).build(),
                new AppointmentBuilder(APPOINTMENT_BENSON).withTags("nose").build(),
                new AppointmentBuilder(SECOND_APPOINTMENT_CARL).withTags("ear").build(),
                new AppointmentBuilder(APPOINTMENT_GEORGE).build()));
    }

    public static List<Appointment> getGroupedAppointmentsByTag() {
        setAll();
        return new ArrayList<>(Arrays.asList(new AppointmentBuilder(APPOINTMENT_DANIEL).build(),
                new AppointmentBuilder(SECOND_APPOINTMENT_GEORGE).build(),
                new AppointmentBuilder(SECOND_APPOINTMENT_CARL).withTags("ear").build(),
                new AppointmentBuilder(APPOINTMENT_BENSON).withTags("nose").build(),
                new AppointmentBuilder(APPOINTMENT_CARL).withTags("throat").build(),
                new AppointmentBuilder(APPOINTMENT_ELLE).build(),
                new AppointmentBuilder(APPOINTMENT_GEORGE).build()));
    }

    public static List<Appointment> getGroupedAppointmentsByPatient() {
        setAll();
        return new ArrayList<>(Arrays.asList(
                new AppointmentBuilder(APPOINTMENT_BENSON).withTags("nose").build(),
                new AppointmentBuilder(APPOINTMENT_CARL).withTags("throat").build(),
                new AppointmentBuilder(SECOND_APPOINTMENT_CARL).withTags("ear").build(),
                new AppointmentBuilder(APPOINTMENT_DANIEL).build(),
                new AppointmentBuilder(APPOINTMENT_ELLE).build(),
                new AppointmentBuilder(SECOND_APPOINTMENT_GEORGE).build(),
                new AppointmentBuilder(APPOINTMENT_GEORGE).build()));
    }
}
