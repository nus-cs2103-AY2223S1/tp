package seedu.address.model.buyer;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.address.Address;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;


/**
 * Represents a Buyer in the buyer book.
 * Guarantees: field values are validated, immutable.
 * Only priceRange and desiredCharacteristics may be null.
 */
public class Buyer {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Optional<PriceRange> priceRange;
    private final Optional<Characteristics> desiredCharacteristics;
    private final Priority priority;

    private final LocalDateTime entryTime;

    /**
     * Every field must be present and not null.
     */
    public Buyer(Name name, Phone phone, Email email, Address address,
                 PriceRange priceRange, Characteristics characteristics,
                 Priority priority, LocalDateTime entryTime) {
        requireAllNonNull(name, phone, email, address, priority, entryTime);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.priceRange = Optional.ofNullable(priceRange);
        this.desiredCharacteristics = Optional.ofNullable(characteristics);
        this.priority = priority;
        this.entryTime = entryTime;
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

    public LocalDateTime getEntryTime() {
        return entryTime;
    }

    public Optional<PriceRange> getPriceRange() {
        return this.priceRange;
    }

    public Optional<Characteristics> getDesiredCharacteristics() {
        return this.desiredCharacteristics;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Priority getPriority() {
        return this.priority;
    }

    /**
     * Returns true if both buyers have the same phone or email.
     * This defines a weaker notion of equality between two buyers.
     */
    public boolean isSameBuyer(Buyer otherBuyer) {
        if (otherBuyer == this) {
            return true;
        }

        return otherBuyer != null
                && (otherBuyer.getPhone().equals(getPhone())
                    || otherBuyer.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both buyers have the same identity and data fields.
     * This defines a stronger notion of equality between two buyers.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Buyer)) {
            return false;
        }

        Buyer otherBuyer = (Buyer) other;
        return otherBuyer.getName().equals(getName())
                && otherBuyer.getPhone().equals(getPhone())
                && otherBuyer.getEmail().equals(getEmail())
                && otherBuyer.getAddress().equals(getAddress())
                && otherBuyer.getPriceRange().equals(getPriceRange())
                && otherBuyer.getDesiredCharacteristics().equals(getDesiredCharacteristics())
                && otherBuyer.getPriority().equals(getPriority());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, priority);
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
                .append(getAddress())
                .append("; Budget: ")
                .append(getPriceRange().map(PriceRange::toString).orElse("Budget: Not Specified"))
                .append("; Desired Characteristics: ")
                .append(getDesiredCharacteristics()
                        .map(Characteristics::toString)
                        .orElse("Not Specified"));

        builder.append(" Priority: ")
                .append(getPriority());

        return builder.toString();
    }

}
