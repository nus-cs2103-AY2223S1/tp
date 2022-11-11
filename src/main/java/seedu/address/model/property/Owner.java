package seedu.address.model.property;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;

/**
 * Encapsulates all attributes for a particular Owner.
 */
public class Owner {

    public final Name name;
    public final Phone phone;

    /**
     * Every field must be present for an Owner to be correctly initialised.
     */
    public Owner(Name name, Phone phone) {
        requireAllNonNull(name, phone);

        this.name = name;
        this.phone = phone;
    }

    public Name getName() {
        return this.name;
    }

    public Phone getPhone() {
        return this.phone;
    }

    /**
     * Returns true if both owners have the same phone number.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Owner)) {
            return false;
        }

        Owner otherOwner = (Owner) other;
        return otherOwner != null
                && otherOwner.getPhone().equals(this.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.name, this.phone);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Owner Name: ")
                .append(getName())
                .append("\nOwner Phone: ")
                .append(getPhone());
        return sb.toString();
    }
}
