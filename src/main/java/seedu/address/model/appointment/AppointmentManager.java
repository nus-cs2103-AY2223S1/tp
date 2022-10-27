package seedu.address.model.appointment;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    private Set<Appointment> appointments;

    /**
     * Creates a new appointment using the given nurse, patient and
     * appointmentStartDateTime
     *
     * @param nurse                    The requested nurse
     * @param patient                  The requested patient
     * @param appointmentStartDateTime The requested appointmentStartDateTime
     * @throws IllegalArgumentException The required arguments were not provided
     *                                  correctly
     * @throws NurseIsBusyException     The nurse is current busy at this point of
     *                                  time
     * @throws PatientIsBusyException   The patient is current busy at this point of
     *                                  time
     */
    public void createNewAppointment(Nurse nurse, Patient patient, LocalDateTime appointmentStartDateTime)
            throws IllegalArgumentException, NurseIsBusyException, PatientIsBusyException {
        if (Objects.isNull(nurse) || Objects.isNull(patient) || Objects.isNull(appointmentStartDateTime)) {
            throw new IllegalArgumentException("Patient and Nurse and appointmentStartDateTime must be given");
        }
        Optional<Appointment> appointment = findAppointment(Optional.of(nurse), Optional.of(patient),
                appointmentStartDateTime);
        if (appointment.isPresent()) {
            Appointment foundAppointment = appointment.get();
            if (foundAppointment.hasNurse(nurse)) {
                throw new NurseIsBusyException();
            }
            if (foundAppointment.hasPatient(patient)) {
                throw new PatientIsBusyException();
            }
        }
        Appointment newAppointment = new Appointment(patient, nurse, appointmentStartDateTime);
        this.appointments.add(newAppointment);
    }

    /**
     * Removes an appointment given the nurse, patient and appointmentStartDateTime
     *
     * @param nurse                    The nurse of the appointment to be removed
     * @param patient                  The patient of the appointment to be removed
     * @param appointmentStartDateTime The appointmentStartDateTime of the
     *                                 appointment to be removed
     * @throws IllegalArgumentException     When the appointment start datetime is
     *                                      not given or the patient or nurse is
     *                                      not provided
     * @throws AppointmentNotFoundException When the appointment cannot be found
     */
    public void removeAppointment(Optional<Nurse> nurse, Optional<Patient> patient,
            LocalDateTime appointmentStartDateTime) throws IllegalArgumentException, AppointmentNotFoundException {
        if (Objects.isNull(appointmentStartDateTime)) {
            throw new IllegalArgumentException("Appointment start datetime must be given");
        }
        if (patient.isEmpty() && nurse.isEmpty()) {
            throw new IllegalArgumentException("Patient or Nurse must be given");
        }
        Appointment appointment = findAppointment(nurse, patient, appointmentStartDateTime)
                .orElseThrow(AppointmentNotFoundException::new);
        this.appointments.remove(appointment);
    }

    /**
     * Returns true if the given person has an appointment during the given dateTime
     *
     * @param person   The person to check
     * @param dateTime The dateTime to check
     * @return True if the given person has an appointment during the given dateTime
     */
    public boolean hasAppointment(Person person, LocalDateTime dateTime) {
        return this.appointments.stream().filter(x -> x.involvesPerson(person)).anyMatch(x -> x.isClashing(dateTime));
    }

    /**
     * Changes the nurse for the appointment given the old and new nurse and the
     * appointmentStartDateTime
     *
     * @param oldNurse                 The old nurse associated with the appointment
     * @param newNurse                 The new nurse to be associated with the
     *                                 appointment
     * @param appointmentStartDateTime The appointment's start datetime
     * @throws IllegalArgumentException     When the appointment start date time is
     *                                      not provided
     * @throws IllegalArgumentException     When both the old and new nurses are not
     *                                      provided
     * @throws AppointmentNotFoundException When the appointment cannot be found
     */
    public void changeNurseForAppointment(Nurse oldNurse, Nurse newNurse, LocalDateTime appointmentStartDateTime)
            throws IllegalArgumentException, AppointmentNotFoundException {
        if (Objects.isNull(appointmentStartDateTime)) {
            throw new IllegalArgumentException("Appointment start datetime must be given");
        }
        if (Objects.isNull(oldNurse) || Objects.isNull(newNurse)) {
            throw new IllegalArgumentException("Both Nurses must be given");
        }
        Appointment appointment = findAppointment(Optional.of(oldNurse), Optional.empty(),
                appointmentStartDateTime).orElseThrow(AppointmentNotFoundException::new);
        appointment.setNurse(newNurse);
    }

    /**
     * Returns an optional of an appointment given the nurse, patient and
     * appointmentStartDateTime of the supposed appointment
     *
     * @param nurse                    The nurse of the requested appointment
     * @param patient                  The patient of the requested appointment
     * @param appointmentStartDateTime The appointment start date time of the
     *                                 requested appointment
     * @return The optional of the appointment
     * @throws IllegalArgumentException When the appointment start date time is
     *                                  not provided
     * @throws IllegalArgumentException When both the old and new nurses are not
     *                                  provided
     */
    public Optional<Appointment> findAppointment(Optional<Nurse> nurse, Optional<Patient> patient,
            LocalDateTime appointmentStartDateTime) throws IllegalArgumentException {
        if (Objects.isNull(appointmentStartDateTime)) {
            throw new IllegalArgumentException("Appointment start datetime must be given");
        }
        if (patient.isEmpty() && nurse.isEmpty()) {
            throw new IllegalArgumentException("Patient or Nurse must be given");
        }
        Stream<Appointment> filteredAppointmentsByDateTime = this.appointments.stream()
                .filter(x -> x.getAppointmentStartDateTime().equals(appointmentStartDateTime));
        Stream<Appointment> filteredAppointmentsByPatient = patient
                .map(x -> filteredAppointmentsByDateTime.filter(y -> y.involvesPerson(x)))
                .orElse(filteredAppointmentsByDateTime);
        Stream<Appointment> filteredAppointmentsByNurse = nurse
                .map(x -> filteredAppointmentsByDateTime.filter(y -> y.involvesPerson(x)))
                .orElse(filteredAppointmentsByPatient);
        assert filteredAppointmentsByNurse.count() <= 1;
        return filteredAppointmentsByNurse.findFirst();
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
