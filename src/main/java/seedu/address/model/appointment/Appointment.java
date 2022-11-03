package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Represents an appointment between a patient and a nurse.
 */
public class Appointment implements Comparable<Appointment> {
    private Patient patient;
    private Nurse nurse;
    private AppointmentDateTime appointmentDateTime;
    private Boolean visited;

    /**
     * Appointment Constructor
     *
     * @param patient             The provided patient
     * @param nurse               The provided nurse
     * @param appointmentDateTime The provided appointment datetime
     */
    public Appointment(Patient patient, Nurse nurse, AppointmentDateTime appointmentDateTime) {
        requireAllNonNull(patient, nurse, appointmentDateTime);
        this.patient = patient;
        this.nurse = nurse;
        this.appointmentDateTime = appointmentDateTime;
        this.visited = false;
    }

    /**
     * @return The appointmentDateTime
     */
    public AppointmentDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    /**
     * @return The nurse
     */
    public Nurse getNurse() {
        return nurse;
    }

    /**
     * @return The patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * @return The visited status
     */
    public Boolean getVisited() {
        return visited;
    }

    /**
     * @param appointmentDateTime The appointmentDateTime to set
     */
    public void setAppointmentDateTime(AppointmentDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    /**
     * @param nurse The nurse to set
     */
    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    /**
     * @param patient The patient to set
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * @param visited The visited to set
     */
    public void setVisited(Boolean visited) {
        this.visited = visited;
    }

    /**
     * Returns true if The given person is involved in this appointment
     *
     * @param testPerson The person to test against
     * @return True if The given person is involved in this appointment
     */
    public boolean involvesPerson(Person testPerson) {
        return patient.equals(testPerson) || nurse.equals(testPerson);
    }

    /**
     * Returns true if The appointment involves The given patient
     *
     * @param patient The given patient
     * @return True if The appointment involves The given patient
     */
    public boolean hasPatient(Patient patient) {
        return patient.equals(patient);
    }

    /**
     * Returns true if The appointment involves The given nurse
     *
     * @param nurse The given nurse
     * @return True if The appointment involves The given nurse
     */
    public boolean hasNurse(Nurse nurse) {
        return nurse.equals(nurse);
    }

    @Override
    public boolean equals(Object o) {
        return o == this // short circuit if same object
                || (o instanceof Appointment // instanceof handles nulls
                        && patient.equals(((Appointment) o).patient)
                        && nurse.equals(((Appointment) o).nurse)
                        && appointmentDateTime.equals(((Appointment) o).appointmentDateTime)); // state check
    }

    @Override
    public int compareTo(Appointment o) {
        return getAppointmentDateTime().compareTo(o.getAppointmentDateTime());
    }

    /**
     * Links the current appointment to its nurse and patient
     */
    public void create() {
        nurse.addAppointment(this);
        patient.addAppointment(this);
    }

    /**
     * Unlinks the current appointment to its nurse and patient
     */
    public void delete() {
        nurse.removeAppointment(this);
        patient.removeAppointment(this);
    }

    /**
     * Links the current appointment to its new nurse
     *
     * @param newNurse The new nurse to link to
     */
    public void changeNurseTo(Nurse newNurse) {
        nurse.removeAppointment(this);
        setNurse(newNurse);
        nurse.addAppointment(this);
    }
}
