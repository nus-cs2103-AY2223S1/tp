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
    private final Room room;
    private final DateRange dateRange;
    private final NumberOfGuests numberOfGuests;
    private final IsRoomClean isRoomClean;
    private final Bill bill;
    private final Request request;

    /**
     * Every field must be present and not null.
     */
    public Guest(Name name, Phone phone, Email email, Room room, DateRange dateRange,
                 NumberOfGuests numberOfGuests, IsRoomClean isRoomClean, Bill bill, Request request) {
        requireAllNonNull(name, phone, email, dateRange, numberOfGuests, isRoomClean, bill, request);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.room = room;
        this.dateRange = dateRange;
        this.numberOfGuests = numberOfGuests;
        this.isRoomClean = isRoomClean;
        this.bill = bill;
        this.request = request;
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

    public Room getRoom() {
        return room;
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

    public Bill getBill() {
        return bill;
    }

    public Request getRequest() {
        return request;
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
     * Returns true if both guests have the same room.
     * This defines a weaker notion of equality between two guests.
     */
    public boolean isSameRoom(Guest otherGuest) {
        if (otherGuest == this) {
            return true;
        }

        return otherGuest != null
                && otherGuest.getRoom().equals(getRoom());
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
                && otherGuest.getRoom().equals(getRoom())
                && otherGuest.getDateRange().equals(getDateRange())
                && otherGuest.getNumberOfGuests().equals(getNumberOfGuests())
                && otherGuest.getIsRoomClean().equals(getIsRoomClean())
                && otherGuest.getBill().equals(getBill())
                && otherGuest.getRequest().equals(getRequest());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, dateRange, numberOfGuests, isRoomClean, bill, request);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append("; Phone: ")
                .append(getPhone())
                .append("; Email: ")
                .append(getEmail())
                .append("; Room: ")
                .append(getRoom())
                .append("; Date Range: ")
                .append(getDateRange())
                .append("; No. of Guests: ")
                .append(getNumberOfGuests())
                .append("; Is Room Clean: ")
                .append(getIsRoomClean())
                .append("; Bill: ")
                .append(getBill())
                .append("; Request: ")
                .append(getRequest());

        return builder.toString();
    }
}
