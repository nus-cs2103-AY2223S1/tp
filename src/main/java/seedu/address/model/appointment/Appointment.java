package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Represents an appointment between a patient and a nurse.
 */
public class Appointment {
    private Patient patient;
    private Nurse nurse;
    private LocalDateTime startDateTime;
    private Boolean visited;

    /**
     * Appointment Constructor
     *
     * @param patient  The provided patient
     * @param nurse    The provided nurse
     * @param dateTime The provided start date time
     */
    public Appointment(Patient patient, Nurse nurse, LocalDateTime dateTime) {
        requireAllNonNull(patient, nurse, dateTime);
        this.patient = patient;
        this.nurse = nurse;
        this.startDateTime = dateTime;
        this.visited = false;
    }

    /**
     * @return the dateTime
     */
    public LocalDateTime getAppointmentStartDateTime() {
        return startDateTime;
    }

    /**
     * @return the nurse
     */
    public Nurse getNurse() {
        return nurse;
    }

    /**
     * @return the patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @return the visited
     */
    public Boolean getVisited() {
        return visited;
    }

    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.startDateTime = dateTime;
    }

    /**
     * @param nurse the nurse to set
     */
    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    /**
     * @param patient the patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @param visited the visited to set
     */
    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns if the given person is involved in this appointment
     *
     * @param testPerson The person to test against
     * @return True if the given person is involved in this appointment
     */
    public boolean involvesPerson(Person testPerson) {
        return this.patient.equals(testPerson) || this.nurse.equals(testPerson);
    }

    /**
     * Returns if the given datetime will clash will this appointment
     *
     * @param testDateTime The test date time
     * @return True if the given datetime will clash will this appointment
     */
    public boolean isClashing(LocalDateTime testDateTime) {
        return withinPeriod(startDateTime, testDateTime, 120L);
    }

    /**
     * Returns true if the two date times are with the period given by the interval
     * parameter
     *
     * @param dateTime1         The first date time
     * @param dateTime2         The second date time
     * @param intervalInMinutes The interval in minutes
     * @return True if the two date times are with the period given by the interval
     *         parameter
     */
    private boolean withinPeriod(LocalDateTime dateTime1, LocalDateTime dateTime2, long intervalInMinutes) {
        long minutes = ChronoUnit.MINUTES.between(dateTime1, dateTime2);
        return minutes < intervalInMinutes;
    }

    /**
     * Returns true if the appointment involves the given patient
     *
     * @param patient The given patient
     * @return True if the appointment involves the given patient
     */
    public boolean hasPatient(Patient patient) {
        return this.patient.equals(patient);
    }

    /**
     * Returns true if the appointment involves the given nurse
     *
     * @param nurse The given nurse
     * @return True if the appointment involves the given nurse
     */
    public boolean hasNurse(Nurse nurse) {
        return this.nurse.equals(nurse);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Appointment // instanceof handles nulls
                && patient.equals(((Appointment) other).patient)
                && nurse.equals(((Appointment) other).nurse)
                && startDateTime.equals(((Appointment) other).startDateTime)); // state check
    }
}
