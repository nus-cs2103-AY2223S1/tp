package seedu.address.model.appointment;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.appointment.exceptions.AppointmentNotFoundException;
import seedu.address.model.appointment.exceptions.NurseIsBusyException;
import seedu.address.model.appointment.exceptions.PatientIsBusyException;
import seedu.address.model.person.Nurse;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Person;

/**
 * Represents an appointment manager that manages the appointments between a
 * patients and nurses.
 */
public class AppointmentManager {
    private final Set<Appointment> appointments;

    /**
     * Appointment Manager Constructor
     */
    public AppointmentManager() {
        this.appointments = new HashSet<>();
    }

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
        this.appointments.add(newAppointment);
        return newAppointment;
    }

    /**
     * Removes an appointment given the nurse, patient and appointmentDateTime
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
    public void removeAppointment(Optional<Patient> patient, Optional<Nurse> nurse,
            AppointmentDateTime appointmentDateTime) throws IllegalArgumentException, AppointmentNotFoundException {
        requireAllNonNull(patient, nurse, appointmentDateTime);
        if (patient.isEmpty() && nurse.isEmpty()) {
            throw new IllegalArgumentException("Patient or Nurse must be given");
        }
        Appointment appointment = findAppointment(nurse, patient, appointmentDateTime)
                .orElseThrow(AppointmentNotFoundException::new);
        this.appointments.remove(appointment);
    }

    /**
     * Returns true if the given person has an appointment during the given dateTime
     *
     * @param person              The person to check
     * @param appointmentDateTime The appointment date time to check
     * @return True if the given person has an appointment during the given dateTime
     */
    public boolean hasAppointment(Person person, AppointmentDateTime appointmentDateTime) {
        return this.appointments.stream().filter(x -> x.involvesPerson(person))
                .anyMatch(x -> x.getAppointmentDateTime().equals(appointmentDateTime));
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
            throws IllegalArgumentException, AppointmentNotFoundException {
        if (Objects.isNull(appointmentDateTime)) {
            throw new IllegalArgumentException("Appointment start datetime must be given");
        }
        if (Objects.isNull(oldNurse) || Objects.isNull(newNurse)) {
            throw new IllegalArgumentException("Both Nurses must be given");
        }
        Appointment appointment = findAppointment(Optional.of(oldNurse), Optional.empty(),
                appointmentDateTime).orElseThrow(AppointmentNotFoundException::new);
        appointment.setNurse(newNurse);
    }

    /**
     * Returns an optional of an appointment given the nurse, patient and
     * appointmentDateTime of the supposed appointment
     *
     * @param nurse               The nurse of the requested appointment
     * @param patient             The patient of the requested appointment
     * @param appointmentDateTime The appointment start date time of the requested
     *                            appointment
     * @return The optional of the appointment
     * @throws IllegalArgumentException When the appointment start date time is
     *                                  not provided
     * @throws IllegalArgumentException When both the old and new nurses are not
     *                                  provided
     */
    public Optional<Appointment> findAppointment(Optional<Nurse> nurse, Optional<Patient> patient,
            AppointmentDateTime appointmentDateTime) throws IllegalArgumentException {
        if (Objects.isNull(appointmentDateTime)) {
            throw new IllegalArgumentException("Appointment start datetime must be given");
        }
        if (patient.isEmpty() && nurse.isEmpty()) {
            throw new IllegalArgumentException("Patient or Nurse must be given");
        }
        try {
            return this.appointments.stream()
                    .filter(x -> x.getAppointmentDateTime().equals(appointmentDateTime))
                    .filter(x -> nurse.map(n -> x.getNurse().equals(n)).orElse(false)
                            || patient.map(p -> x.getPatient().equals(p)).orElse(false))
                    .findFirst();
        } catch (NullPointerException e) {
            return Optional.empty();
        }

    }

    /**
     * Returns the set of appointments of a given patient
     *
     * @param patient The given patient
     * @return The set of appointments of the given patient
     */
    public Set<Appointment> getAppointmentsByPatient(Patient patient) {
        return this.appointments.stream().filter(appointment -> appointment.hasPatient(patient))
                .collect(Collectors.toSet());
    }

    /**
     * Returns the set of appointments of a given patient
     *
     * @param nurse The given nurse
     * @return The set of appointments of the given nurse
     */
    public Set<Appointment> getAppointmentsByNurse(Nurse nurse) {
        return this.appointments.stream().filter(appointment -> appointment.hasNurse(nurse))
                .collect(Collectors.toSet());
    }

}
