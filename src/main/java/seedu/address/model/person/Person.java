package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person implements Comparable<Person> {

    private static final String VALID_PHONE_NUMBER = "91234567";
    private static final String VALID_EMAIL = "dinosaur@gmail.com";

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Email email, Phone phone) {
        requireAllNonNull(name, email, phone);
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    /**
     * Constructs a {@code Person} object with the specified name, and default email and phone.
     *
     * @param name The name of the to be constructed person object.
     */
    public Person(Name name) {
        this.name = name;
        this.email = new Email(VALID_EMAIL);
        this.phone = new Phone(VALID_PHONE_NUMBER);
    }

    public Name getName() {
        return name;
    }

    public Email getEmail() {
        return email;
    }

    public Phone getPhone() {
        return phone;
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
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getPhone().equals(getPhone());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, email, phone);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Email: ")
                .append(getEmail())
                .append("; Phone: ")
                .append(getPhone());
        return builder.toString();
    }

    @Override
    public int compareTo(Person other) {
        return name.compareTo(other.name);
    }
}
