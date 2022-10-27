package seedu.address.model.appointment;

import seedu.address.model.person.DateTime;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;

public class Appointment {
    private Patient patient;
    private Nurse nurse;
    private DateTime dateTime;
    private Boolean visited;

    public Appointment(Patient patient, Nurse nurse, DateTime dateTime) {
        this.patient = patient;
        this.nurse = nurse;
        this.dateTime = dateTime;
        this.visited = false;
    }

    /**
     * @return the dateTime
     */
    public DateTime getDateTime() {
        return dateTime;
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
    public void setDateTime(DateTime dateTime) {
        this.dateTime = dateTime;
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
}
