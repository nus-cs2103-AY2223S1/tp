package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.date.Date;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    private static final int AGE_GROUP_SIZE = 5;

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Address address;
    private final Gender gender;
    private final Date dob;
    private final Uid uid;

    /**
     * Constructor to create new Person object.
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Gender gender, Date dob, Uid uid) {
        requireAllNonNull(name, phone, email, address, gender, uid);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.dob = dob;
        this.gender = gender;
        this.uid = uid;
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

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }
    public Date getDob() {
        return dob;
    }
    public Uid getUid() {
        return uid;
    }

    /**
     * Returns the age group range of a Person.
     */
    public String getAgeGroup() {
        int minAgeInGroup = dob.toAge();
        minAgeInGroup = (int) (Math.floor(minAgeInGroup / AGE_GROUP_SIZE) * AGE_GROUP_SIZE);
        int maxAgeInGroup = minAgeInGroup + AGE_GROUP_SIZE;
        return String.format("%d-%dyo", minAgeInGroup, maxAgeInGroup);
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
     * Uid is not included in equality check.
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
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getDob().equals(getDob());
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, gender, dob);
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
                .append("; Gender: ")
                .append(getGender())
                .append("; Date of birth: ")
                .append(getDob());

        return builder.toString();
    }
}
