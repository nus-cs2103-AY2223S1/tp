package coydir.model.person;

import static coydir.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import coydir.model.tag.Tag;

/**
 * Represents a Person in the database.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final EmployeeId employeeId;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Position position;
    private final Department department;
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final Set<Leave> leaves = new HashSet<>();
    private final int totalNumberOfLeaves;
    private int leavesLeft = 0;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, EmployeeId employeeId, Phone phone, Email email, Position position,
            Department department, Address address, Set<Tag> tags, int numberOfLeaves) {
        requireAllNonNull(name, employeeId, position);
        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.position = position;
        this.department = department;
        this.address = address;
        this.tags.addAll(tags);
        this.totalNumberOfLeaves = numberOfLeaves;
        this.leavesLeft = numberOfLeaves;
    }

    public Name getName() {
        return this.name;
    }

    public EmployeeId getEmployeeId() {
        return this.employeeId;
    }

    public Phone getPhone() {
        return this.phone;
    }

    public Email getEmail() {
        return this.email;
    }

    public Position getPosition() {
        return this.position;
    }

    public Department getDepartment() {
        return this.department;
    }

    public Address getAddress() {
        return this.address;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    public void addLeave(Leave toAdd) {
        this.leaves.add(toAdd);
    }

    public void deleteLeave(Leave toDelete) {
        this.leaves.remove(toDelete);
    }

    public Set<Leave> getLeaves() {
        return Collections.unmodifiableSet(leaves);
    }

    public int getTotalNumberOfLeaves() {
        return this.totalNumberOfLeaves;
    }

    public int getLeavesLeft() {
        return this.leavesLeft;
    }

    public void setLeavesLeft(int leavesLeft) {
        this.leavesLeft = leavesLeft;
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
                && otherPerson.getEmployeeId().equals(getEmployeeId())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getPosition().equals(getPosition())
                && otherPerson.getDepartment().equals(getDepartment())
                && otherPerson.getAddress().equals(getAddress())
                && otherPerson.getTags().equals(getTags())
                && otherPerson.getLeaves().equals(getLeaves())
                && otherPerson.getTotalNumberOfLeaves() == getTotalNumberOfLeaves()
                && otherPerson.getLeavesLeft() == getLeavesLeft();
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, employeeId, phone, email, position, department, address, tags,
                leaves, totalNumberOfLeaves, leavesLeft);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Employee ID: ")
                .append(getEmployeeId())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Position: ")
                .append(getPosition())
                .append("; Department: ")
                .append(getDepartment())
                .append("; Address: ")
                .append(getAddress())
                .append("; Total Leaves: ")
                .append(getTotalNumberOfLeaves())
                .append("; Number of Leaves Left: ")
                .append(getLeavesLeft());

        Set<Tag> tags = getTags();
        if (!tags.isEmpty()) {
            builder.append("; Tags: ");
            tags.forEach(builder::append);
        }

        Set<Leave> leaves = getLeaves();
        if (!leaves.isEmpty()) {
            builder.append("; Leaves: ");
            leaves.forEach(builder::append);
        }

        return builder.toString();
    }

}
