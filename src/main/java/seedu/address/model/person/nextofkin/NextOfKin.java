package seedu.address.model.person.nextofkin;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a NextOfKin of a student.
 */
public class NextOfKin extends Person {

    private final Relationship relationship;

    /**
     * Every field must be present and not null.
     */
    public NextOfKin(Name name, Phone phone, Email email, Address address, Set<Tag> tags, Relationship relationship) {
        super(name, phone, email, address, tags);
        requireAllNonNull(relationship);
        this.relationship = relationship;
    }

    public Relationship getRelationship() {
        return relationship;
    }

    /**
     * Returns true if both next of kins have the same identity and data fields.
     * This defines a stronger notion of equality between two next of kins.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof NextOfKin)) {
            return false;
        }

        NextOfKin otherNextOfKin = (NextOfKin) other;
        return otherNextOfKin.getName().equals(getName())
                && otherNextOfKin.getPhone().equals(getPhone())
                && otherNextOfKin.getEmail().equals(getEmail())
                && otherNextOfKin.getAddress().equals(getAddress())
                && otherNextOfKin.getTags().equals(getTags())
                && otherNextOfKin.getRelationship().equals(getRelationship());
    }

    /**
     * Returns true if this next of kin and {@code otherPerson} are same except tags.
     * This defines a weaker notion of equality between two persons.
     */
    @Override
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        if (!(otherPerson instanceof NextOfKin)) {
            return false;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getAddress().equals(getAddress())
                && ((NextOfKin) otherPerson).getRelationship().equals(getRelationship());

    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(this.getName(), this.getPhone(), this.getEmail(), this.getAddress(),
                this.getTags(), this.relationship);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();

        builder.append(super.toString())
                .append("; Relationship: ")
                .append(getRelationship());

        return builder.toString();
    }
}
