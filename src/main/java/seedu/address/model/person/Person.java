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
public abstract class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final Gender gender;

    // Data fields
    private final Location location;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Gender gender, Set<Tag> tags, Location location) {
        requireAllNonNull(name, phone, email, gender, tags, location);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.tags.addAll(tags);
        this.location = location;
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

    public Gender getGender() {
        return gender;
    }

    public Location getLocation() {
        return location;
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
                && otherPerson.getGender().equals(getGender())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLocation().equals(getLocation());
    }


    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, gender, tags, location);
    }

    /**
     * Return 1 if this Person's name has higher precedence
     * lexicographically over the target's name.
     * 0 if their precedence is the same.
     * -1 if this Person's name has lower precedence
     * over the target's name.
     * @param person target Person of comparison.
     * @return int.
     */
    public int compareName(Person person) {
        return this.name.toString().compareTo(person.name.toString());
    }

    /**
     * Return 1 if this Person's moduleCode has higher precedence
     * lexicographically over the target's moduleCode.
     * 0 if their precedence is the same.
     * -1 if this Person's moduleCode has lower precedence
     * over the target's moduleCode. For Person objects without
     * moduleCode field. They will be compared based on their name.
     * @param person target Person of comparison.
     * @return int.
     */
    public int compareModuleCode(Person person) {
        if (person instanceof Student) {
            return compareName(person);
        }
        return -1;
    }

    public abstract String getTypeString();
}
