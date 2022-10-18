package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.assignment.Assignment;
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
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final HashMap<String, ArrayList<Assignment>> assignments = new HashMap<>();
    private final ArrayList<PersonGroup> personGroups = new ArrayList<>();
    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags,
                  HashMap<String, ArrayList<Assignment>> assignments,
                  List<PersonGroup> personGroups) {
        requireAllNonNull(name, phone, email, address, tags, personGroups);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.assignments.putAll(assignments);
        this.personGroups.addAll(personGroups);
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

    public HashMap<String, ArrayList<Assignment>> getAssignments() {
        //Sort based on task workload and deadline
        this.assignments.forEach((key, value) -> {
            Collections.sort(value);
        });
        return this.assignments;
    }

    public ArrayList<PersonGroup> getPersonGroups() {
        return this.personGroups;
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
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getAssignments().equals(getAssignments())
                && otherPerson.getPersonGroups().equals(getPersonGroups());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, assignments, personGroups);
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

        HashMap<String, ArrayList<Assignment>> assignments = getAssignments();
        if (!assignments.isEmpty()) {
            builder.append("; Assignment: ");
            assignments.forEach((key, value) -> {
                String assignment = key + " " + value;
                builder.append(assignment);
            });

        }

        ArrayList<PersonGroup> personGroupsList = getPersonGroups();
        if (!personGroupsList.isEmpty()) {
            builder.append("; Group: ");
            personGroupsList.forEach(builder::append);
        }

        return builder.toString();
    }

}
