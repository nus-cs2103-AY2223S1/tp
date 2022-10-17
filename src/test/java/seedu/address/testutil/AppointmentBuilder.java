package seedu.address.testutil;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

/**
 * A utility class to help with building Appointment objects.
 */
public class AppointmentBuilder {
    public static final String DEFAULT_REASON = "Cough";
    public static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.parse("2000-12-31T00:00");
    public static final List<Integer> DEFAULT_TIME_PERIOD = Arrays.asList(0, 0, 0);
    public static final boolean DEFAULT_MARK_STATUS = false;
    public static final Person DEFAULT_PATIENT = new PersonBuilder().build();

    private String reason;
    private LocalDateTime dateTime;
    private List<Integer> timePeriod;
    private boolean isMarked;
    private Person patient;

    /**
     * Creates a {@code AppointmentBuilder} with the default details.
     */
    public AppointmentBuilder() {
        reason = DEFAULT_REASON;
        dateTime = DEFAULT_DATE_TIME;
        isMarked = DEFAULT_MARK_STATUS;
        patient = DEFAULT_PATIENT;
        timePeriod = DEFAULT_TIME_PERIOD;
    }

    /**
     * Initializes the AppointmentBuilder with the data of {@code appointmentToCopy}.
     */
    public AppointmentBuilder(Appointment appointmentToCopy) {
        reason = appointmentToCopy.getReason();
        dateTime = appointmentToCopy.getDateTime();
        isMarked = appointmentToCopy.isMarked();
        patient = new PersonBuilder(appointmentToCopy.getPatient()).build();
    }

    /**
     * Sets the {@code reason} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    /**
     * Sets the {@code dateTime} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Sets the {@code timePeriod} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withTimePeriod(List<Integer> timePeriod) {
        this.timePeriod = timePeriod;
        return this;
    }

    /**
     * Sets the {@code status} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withMarkStatus(boolean status) {
        this.isMarked = status;
        return this;
    }

    /**
     * Sets the {@code patient} of the {@code Appointment} that we are building.
     */
    public AppointmentBuilder withPatient(Person patient) {
        this.patient = patient;
        return this;
    }

    /**
     * Builds an {@code Appointment} object based on the info we have.
     *
     * @return An {@code Appointment}
     */
    public Appointment build() {
        Appointment appointment = new Appointment(reason, dateTime, timePeriod, isMarked);
        appointment.setPatient(patient);
        return appointment;
    }
}
