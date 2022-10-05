package foodwhere.model.stall;

import static foodwhere.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import foodwhere.model.detail.Detail;

/**
 * Represents a Stall in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Stall {

    // Identity fields
    private final Name name;
    private final Phone phone;

    // Data fields
    private final Address address;
    private final Set<Detail> details = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Stall(Name name, Phone phone, Address address, Set<Detail> details) {
        requireAllNonNull(name, phone, address, details);
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.details.addAll(details);
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Address getAddress() {
        return address;
    }

    /**
     * Returns an immutable detail set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Detail> getDetails() {
        return Collections.unmodifiableSet(details);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Stall otherStall) {
        if (otherStall == this) {
            return true;
        }

        return otherStall != null
                && otherStall.getName().equals(getName());
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

        if (!(other instanceof Stall)) {
            return false;
        }

        Stall otherStall = (Stall) other;
        return otherStall.getName().equals(getName())
                && otherStall.getPhone().equals(getPhone())
                && otherStall.getAddress().equals(getAddress())
                && otherStall.getDetails().equals(getDetails());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, address, details);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Address: ")
                .append(getAddress());

        Set<Detail> details = getDetails();
        if (!details.isEmpty()) {
            builder.append("; Details: ");
            details.forEach(builder::append);
        }
        return builder.toString();
    }

}
