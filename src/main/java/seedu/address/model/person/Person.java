package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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
    private final DateOfBirth dob;

    // Data fields
    private final Address address;
    private final Gender gender; // add gender attribute for person
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Overloaded constructor that takes in optional parameter gender
     */
    public Person(Name name, Phone phone, Email email, DateOfBirth dob, Address address, Set<Tag> tags, Gender gender) {
        requireAllNonNull(name, phone, email, address, tags, gender);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.tags.addAll(tags);
        this.gender = gender;
    }

    /**
     * Every field must be present and not null.
     * Gender field is added at the end of each method.
     */
    public Person(Name name, Phone phone, Email email, DateOfBirth dob, Address address, Set<Tag> tags) {
        requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dob = dob;
        this.address = address;
        this.tags.addAll(tags);
        this.gender = Gender.getNoGender();
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

    public DateOfBirth getDob() {
        return dob;
    }

    public Address getAddress() {
        return address;
    }

    public Gender getGender() {
        return gender;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
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
                && otherPerson.getDob().equals(getDob())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getGender().equals(getGender()); // add gender field for comparison
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, gender); //add gender field
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
                .append(getAddress());

        if (!getDob().isEmpty()) { //Need check if DateOfBirth is empty
            builder.append("; Date of Birth: ")
                .append(getDob());
        }

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        // add gender field in toString method
        builder.append("; Gender: ").append(getGender());
        return builder.toString();
    }
}
