package taskbook.model.person;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import taskbook.commons.util.CollectionUtil;
import taskbook.model.tag.Tag;

/**
 * Represents a Person in the task book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        CollectionUtil.requireAllNonNull(name, phone, email, address, tags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
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
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags);
    }

    /**
     * Returns true if a given person is a valid person.
     */
    public static boolean isValidPerson(Person test) {
        return Name.isValidName(test.getName().fullName)
            && Phone.isValidPhone(test.getPhone().value)
            && Email.isValidEmail(test.getEmail().value)
            && Address.isValidAddress(test.getAddress().value);
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

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }
        return builder.toString();
    }

    /**
     * Compares this person and the other person to decide name alphabetical order.
     * @param other input person.
     * @return 1 if this person's name is alphabetically first, -1 otherwise.
     */
    public int compareByNameAlphabeticalTo(Person other) {
        return this.getName().compareByAlphabeticalTo(other.getName());
    }

    /**
     * Compares this person and the other person to decide name reversealphabetical order.
     * @param other input person.
     * @return -1 if this person's name is alphabetically first, 1 otherwise.
     */
    public int compareByNameReverseAlphabeticalTo(Person other) {
        return this.getName().compareByReverseAlphabeticalTo(other.getName());
    }

    /**
     * Checks of this person's name contains the query.
     * @param query input query.
     * @return true if the query exists exactly in this task, false otherwise.
     */
    public boolean isQueryInPerson(String query) {
        requireNonNull(query);
        return getName().isQueryInName(query);
    }

    /**
     * Compares this person's phone number and another person's phone number to determine ascending order.
     * @param other person.
     * @return positive integer if this person's phone number is numerically greater, 0 if equal, negative otherwise.
     */
    public int compareByPhoneNumberAscendingTo(Person other) {
        return this.getPhone().comparePhoneNumberAscending(other.getPhone());
    }

    /**
     * Compares this person's phone number and another person's phone number to determine descending order.
     * @param other person.
     * @return positive integer if this person's phone number is numerically smaller, 0 if equal, negative otherwise.
     */
    public int compareByPhoneNumberDescendingTo(Person other) {
        return this.getPhone().comparePhoneNumberDescending(other.getPhone());
    }
}
