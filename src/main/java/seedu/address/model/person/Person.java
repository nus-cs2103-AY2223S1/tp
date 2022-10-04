package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Medication;
import seedu.address.model.tag.Tag;

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
    private final HospitalWing hospitalWing;
    private final FloorNumber floorNumber;
    private final WardNumber wardNumber;
    private final Set<Medication> medications = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, NextOfKin nextOfKin, PatientType patientType,
                  HospitalWing hospitalWing, FloorNumber floorNumber,
                  WardNumber wardNumber, Set<Medication> medications) {
        requireAllNonNull(name, phone, email, nextOfKin, patientType, medications);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.nextOfKin = nextOfKin;
        this.patientType = patientType;
        this.hospitalWing = hospitalWing;
        this.floorNumber = floorNumber;
        this.wardNumber = wardNumber;
        this.medications.addAll(medications);
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

    public FloorNumber getFloorNumber() {
        return floorNumber;
    }


    public HospitalWing getHospitalWing() {
        return hospitalWing;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Medication> getMedications() {
        return Collections.unmodifiableSet(medications);
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
        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getHospitalWing().equals(getHospitalWing())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, hospitalWing, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Address: ")
                .append(getAddress())
                .append("; Hospital Wing: ")
                .append(getHospitalWing());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

}

