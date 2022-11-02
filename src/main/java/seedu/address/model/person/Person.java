package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.appointment.PastAppointment;
import seedu.address.model.appointment.UpcomingAppointment;
import seedu.address.model.tag.Medication;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final NextOfKin nextOfKin;
    private final PatientType patientType;
    private final Optional<HospitalWing> hospitalWing;
    private final Optional<FloorNumber> floorNumber;
    private final Optional<WardNumber> wardNumber;
    private final Set<Medication> medications = new HashSet<>();
    private final List<PastAppointment> pastAppointments;
    private final Optional<UpcomingAppointment> upcomingAppointment;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, NextOfKin nextOfKin, PatientType patientType,
                  HospitalWing hospitalWing, FloorNumber floorNumber, WardNumber wardNumber,
                  Set<Medication> medications, List<PastAppointment> pastAppointments,
                  UpcomingAppointment upcomingAppointment) {
        requireAllNonNull(name, phone, email, nextOfKin, patientType, medications, pastAppointments);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nextOfKin = nextOfKin;
        this.patientType = patientType;
        this.hospitalWing = Optional.ofNullable(hospitalWing);
        this.floorNumber = Optional.ofNullable(floorNumber);
        this.wardNumber = Optional.ofNullable(wardNumber);
        this.medications.addAll(medications);
        this.pastAppointments = pastAppointments;
        this.upcomingAppointment = Optional.ofNullable(upcomingAppointment);
    }

    /**
     * Adds input {@code PastAppointment} to stored list of {@code PastAppointment}s.
     * @param appt the {@code PastAppointment} to be added
     */
    public void addPastAppointment(PastAppointment appt) {
        // TODO optimise
        int length = pastAppointments.size();
        LocalDate apptDate = appt.getDate();

        // if appt is equal to any current past appointment, do not add as duplicate
        for (int i = 0; i < length; i++) {
            if (pastAppointments.get(i).equals(appt)) {
                return;
            }
        }

        for (int i = 0; i < length; i++) {
            LocalDate currentApptDate = pastAppointments.get(i).getDate();
            if (apptDate.compareTo(currentApptDate) > 0) { //apptDate is more recent than currentApptDate
                pastAppointments.add(i, appt);
                break;
            }
        }

        if (pastAppointments.size() == length) { //an appointment has not been added
            pastAppointments.add(appt);
        }
    }

    /**
     * Removes the most recent {@code PastAppointment} from the stored list of {@code PastAppointment}s.
     */
    public void deleteMostRecentPastAppointment() throws CommandException {
        try {
            deletePastAppointment(0);
        } catch (IndexOutOfBoundsException e) {
            throw new CommandException("No past appointments to delete.");
        }
    }

    private void deletePastAppointment(int index) {
        pastAppointments.remove(index);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public NextOfKin getNextOfKin() {
        return nextOfKin;
    }

    public PatientType getPatientType() {
        return patientType;
    }

    public Optional<HospitalWing> getHospitalWing() {
        return hospitalWing;
    }

    public Optional<WardNumber> getWardNumber() {
        return wardNumber;
    }

    public Optional<FloorNumber> getFloorNumber() {
        return floorNumber;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Medication> getMedications() {
        return Collections.unmodifiableSet(medications);
    }

    /**
     * Returns a string representation of the medications.
     */
    public String getMedicationString() {
        StringBuilder sb = new StringBuilder("Medication: ");
        getMedications().stream().sorted(Comparator.comparing(medication -> medication.medicationName))
                .forEach(medication -> sb.append(medication.medicationName).append(", "));
        // remove trailing comma
        sb.delete(sb.length() - 2, sb.length());
        return sb.toString();
    }

    /**
     * Returns list of {@code PastAppointment}s tagged to this patient.
     * @return the list of {@code PastAppointment}s
     */
    public List<PastAppointment> getPastAppointments() {
        return pastAppointments;
    }

    public Optional<UpcomingAppointment> getUpcomingAppointment() {
        return upcomingAppointment;
    }

    /**
     * Returns count of {@code PastAppointment}s to this patient.
     * @return the count of {@code PastAppointment}s
     */
    public int getPastAppointmentCount() {
        return pastAppointments.size();
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        boolean isEqual = otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getNextOfKin().equals(getNextOfKin())
                && otherPerson.getPatientType().equals(getPatientType())
                && otherPerson.getMedications().equals(getMedications());

        if (otherPerson.getPatientType().isInpatient() && getPatientType().isInpatient()) {
            isEqual = isEqual && otherPerson.getHospitalWing().equals(getHospitalWing())
                    && otherPerson.getFloorNumber().equals(getFloorNumber())
                    && otherPerson.getWardNumber().equals(getWardNumber());
        }
        List<PastAppointment> otherPastAppointments = otherPerson.getPastAppointments();
        if (otherPastAppointments == null && pastAppointments == null) {
            isEqual = isEqual && true;
        } else if (otherPastAppointments == null || pastAppointments == null) {
            isEqual = isEqual && false;
        } else {
            isEqual = isEqual && otherPastAppointments.size() == pastAppointments.size();
            for (int i = 0; i < pastAppointments.size(); i++) {
                isEqual = isEqual && otherPastAppointments.get(i).equals(pastAppointments.get(i));
            }
        }
        return isEqual;
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, nextOfKin, patientType, hospitalWing,
                floorNumber, wardNumber, medications);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; ")
                .append(getPhone())
                .append("; ")
                .append(getEmail())
                .append("; ")
                .append(getNextOfKin())
                .append("; ")
                .append(getPatientType());

        if (getPatientType().isInpatient()) {
            builder.append("; ")
                    .append(getHospitalWing().get())
                    .append("; ")
                    .append(getFloorNumber().get())
                    .append("; ")
                    .append(getWardNumber().get());
        }


        Set<Medication> tags = getMedications();
        if (!tags.isEmpty()) {
            builder.append("; Medications: ");
            tags.forEach(tag -> builder.append(tag.medicationName).append(", "));
        }
        builder.append("; Past Appointments: ").append(getPastAppointmentCount());
        builder.append("; ").append(getUpcomingAppointment().get());
        return builder.toString();
    }

}

