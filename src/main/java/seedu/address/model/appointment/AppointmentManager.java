package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Optional;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.NurseIsBusyException;
import seedu.address.model.appointment.exceptions.PatientIsBusyException;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;

/**
 * Represents an appointment manager that manages the appointments between a
 * patients and nurses.
 */
public class AppointmentManager {
    /**
     * Creates a new appointment using the given nurse, patient and
     * appointmentDateTime
     *
     * @param nurse               The requested nurse
     * @param patient             The requested patient
     * @param appointmentDateTime The requested appointment Date
     * @throws NullPointerException   Any of the required parameters are null
     *                                correctly
     * @throws NurseIsBusyException   The nurse is current busy at this point of
     *                                time
     * @throws PatientIsBusyException The patient is current busy at this point of
     *                                time
     */
    public Appointment createNewAppointment(Patient patient, Nurse nurse, AppointmentDateTime appointmentDateTime)
            throws NurseIsBusyException, PatientIsBusyException {
        requireAllNonNull(patient, nurse, appointmentDateTime);
        if (hasAppointment(patient, appointmentDateTime)) {
            throw new PatientIsBusyException();
        }
        if (hasAppointment(nurse, appointmentDateTime)) {
            throw new NurseIsBusyException();
        }
        Appointment newAppointment = new Appointment(patient, nurse, appointmentDateTime);
        newAppointment.create();
        return newAppointment;
    }

    /**
     * Removes an appointment given the appointmentDateTime and a patient or nurse
     *
     * @param nurse               The nurse of the appointment to be removed
     * @param patient             The patient of the appointment to be removed
     * @param appointmentDateTime The appointmentDateTime of the
     *                            appointment to be removed
     * @throws IllegalArgumentException     When the appointment start datetime is
     *                                      not given or the patient or nurse is
     *                                      not provided
     * @throws AppointmentNotFoundException When the appointment cannot be found
     */
    public void removeAppointment(Patient patient, Nurse nurse, AppointmentDateTime appointmentDateTime)
            throws AppointmentNotFoundException {
        requireAllNonNull(patient, nurse, appointmentDateTime);
        Appointment appointment = findAppointment(nurse, patient, appointmentDateTime)
                .orElseThrow(AppointmentNotFoundException::new);
        appointment.delete();
    }

    /**
     * Returns true if the given nurse has an appointment during the given dateTime
     *
     * @param nurse               The nurse to check
     * @param appointmentDateTime The appointment date time to check
     * @return True if the given nurse has an appointment during the given dateTime
     */
    public boolean hasAppointment(Nurse nurse, AppointmentDateTime appointmentDateTime) {
        return nurse.hasAppointment(appointmentDateTime);
    }

    /**
     * Returns true if the given patient has an appointment during the given
     * dateTime
     *
     * @param patient             The patient to check
     * @param appointmentDateTime The appointment date time to check
     * @return True if the given patient has an appointment during the given
     *         dateTime
     */
    public boolean hasAppointment(Patient patient, AppointmentDateTime appointmentDateTime) {
        return patient.hasAppointment(appointmentDateTime);
    }

    /**
     * Changes the nurse for the appointment given the old and new nurse and the
     * appointmentDateTime
     *
     * @param oldNurse            The old nurse associated with the appointment
     * @param newNurse            The new nurse to be associated with the
     *                            appointment
     * @param appointmentDateTime The appointment's start datetime
     * @throws IllegalArgumentException     When the appointment start date time is
     *                                      not provided
     * @throws IllegalArgumentException     When both the old and new nurses are not
     *                                      provided
     * @throws AppointmentNotFoundException When the appointment cannot be found
     */
    public void changeNurseForAppointment(Nurse oldNurse, Nurse newNurse, AppointmentDateTime appointmentDateTime)
            throws IllegalArgumentException, AppointmentNotFoundException, NurseIsBusyException {
        if (Objects.isNull(appointmentDateTime)) {
            throw new IllegalArgumentException("Appointment start datetime must be given");
        }
        if (Objects.isNull(oldNurse) || Objects.isNull(newNurse)) {
            throw new IllegalArgumentException("Both Nurses must be given");
        }
        Appointment appointment = findAppointment(oldNurse, appointmentDateTime)
                .orElseThrow(AppointmentNotFoundException::new);
        if (hasAppointment(newNurse, appointmentDateTime)) {
            throw new NurseIsBusyException("The new nurse is busy");
        }
        appointment.changeNurseTo(newNurse);
    }

    /**
     * Returns an optional of an appointment given the nurse and the
     * appointmentDateTime of the supposed appointment
     *
     * @param nurse               The nurse of the requested appointment
     * @param appointmentDateTime The appointment start date time of the requested
     *                            appointment
     * @return The optional of the appointment
     */
    public Optional<Appointment> findAppointment(Nurse nurse, AppointmentDateTime appointmentDateTime) {
        requireAllNonNull(nurse, appointmentDateTime);
        return nurse.findAppointment(appointmentDateTime);
    }

    /**
     * Returns an optional of an appointment given the patient and the
     * appointmentDateTime of the supposed appointment
     *
     * @param patient             The patient of the requested appointment
     * @param appointmentDateTime The appointment start date time of the requested
     *                            appointment
     * @return The optional of the appointment
     */
    public Optional<Appointment> findAppointment(Patient patient, AppointmentDateTime appointmentDateTime) {
        requireAllNonNull(patient, appointmentDateTime);
        return patient.findAppointment(appointmentDateTime);
    }

    /**
     * Returns an optional of an appointment given the nurse, patient and the
     * appointmentDateTime of the supposed appointment
     *
     * @param nurse               The nurse of the requested appointment
     * @param patient             The patient of the requested appointment
     * @param appointmentDateTime The appointment start date time of the requested
     *                            appointment
     * @return The optional of the appointment
     */
    public Optional<Appointment> findAppointment(Nurse nurse, Patient patient,
            AppointmentDateTime appointmentDateTime) {
        requireAllNonNull(nurse, patient, appointmentDateTime);
        return patient.findAppointment(nurse, appointmentDateTime);
    }

    // /**
    // * Returns the set of appointments of a given patient
    // *
    // * @param patient The given patient
    // * @return The set of appointments of the given patient
    // */
    // public Set<Appointment> getAppointmentsByPatient(Patient patient) {
    // return this.appointments.stream().filter(appointment ->
    // appointment.hasPatient(patient))
    // .collect(Collectors.toSet());
    // }

    // /**
    // * Returns the set of appointments of a given patient
    // *
    // * @param nurse The given nurse
    // * @return The set of appointments of the given nurse
    // */
    // public Set<Appointment> getAppointmentsByNurse(Nurse nurse) {
    // return this.appointments.stream().filter(appointment ->
    // appointment.hasNurse(nurse))
    // .collect(Collectors.toSet());
    // }

}
