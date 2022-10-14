package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.person.Appointment;
import seedu.address.model.person.Person;

public class AppointmentBuilder {
    public static final String DEFAULT_REASON = "Cough";
    public static final LocalDateTime DEFAULT_DATE_TIME = LocalDateTime.parse("2000-12-31T00:00");
    public static final boolean DEFAULT_MARK_STATUS = false;
    public static final Person DEFAULT_PATIENT = new PersonBuilder().build();

    private String reason;
    private LocalDateTime dateTime;
    private boolean isMarked;
    private Person patient;

    public AppointmentBuilder() {
        reason = DEFAULT_REASON;
        dateTime = DEFAULT_DATE_TIME;
        isMarked = DEFAULT_MARK_STATUS;
        patient = DEFAULT_PATIENT;
    }

    public AppointmentBuilder(Appointment appointmentToCopy) {
        reason = appointmentToCopy.getReason();
        dateTime = appointmentToCopy.getDateTime();
        isMarked = appointmentToCopy.isMarked();
        patient = new PersonBuilder(appointmentToCopy.getPatient()).build();
    }

    public AppointmentBuilder withReason(String reason) {
        this.reason = reason;
        return this;
    }

    public AppointmentBuilder withDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    public AppointmentBuilder withMarkStatus(boolean status) {
        this.isMarked = status;
        return this;
    }

    public AppointmentBuilder withPatient(Person patient) {
        this.patient = patient;
        return this;
    }

    public Appointment build() {
        Appointment appointment = new Appointment(reason, dateTime, isMarked);
        appointment.setPatient(patient);
        return appointment;
    }
}
