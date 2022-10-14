package seedu.guest.model.guest;

import static seedu.guest.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

/**
 * Represents a Guest in the guest book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Guest {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final DateRange dateRange;
    private final NumberOfGuests numberOfGuests;
    private final IsRoomClean isRoomClean;

    /**
     * Every field must be present and not null.
     */
    public Guest(Name name, Phone phone, Email email, DateRange dateRange,
                 NumberOfGuests numberOfGuests, IsRoomClean isRoomClean) {
        requireAllNonNull(name, phone, email, dateRange, numberOfGuests, isRoomClean);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dateRange = dateRange;
        this.numberOfGuests = numberOfGuests;
        this.isRoomClean = isRoomClean;
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

    public DateRange getDateRange() {
        return dateRange;
    }

    public NumberOfGuests getNumberOfGuests() {
        return numberOfGuests;
    }

    public IsRoomClean getIsRoomClean() {
        return isRoomClean;
    }

    /**
     * Returns true if both guests have the same name.
     * This defines a weaker notion of equality between two guests.
     */
    public boolean isSameGuest(Guest otherGuest) {
        if (otherGuest == this) {
            return true;
        }

        return otherGuest != null
                && otherGuest.getName().equals(getName());
    }

    /**
     * Returns true if both guests have the same identity and data fields.
     * This defines a stronger notion of equality between two guests.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Guest)) {
            return false;
        }


        Guest otherGuest = (Guest) other;
        return otherGuest.getName().equals(getName())
                && otherGuest.getPhone().equals(getPhone())
                && otherGuest.getEmail().equals(getEmail())
                && otherGuest.getDateRange().equals(getDateRange())
                && otherGuest.getNumberOfGuests().equals(getNumberOfGuests())
                && otherGuest.getIsRoomClean().equals(getIsRoomClean());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, dateRange, numberOfGuests, isRoomClean);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Date Range: ")
                .append(getDateRange())
                .append("; No. of Guests: ")
                .append(getNumberOfGuests())
                .append("; Is Room Clean: ")
                .append(getIsRoomClean());

        return builder.toString();
    }

}
