package seedu.address.model.property;


import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import seedu.address.model.address.Address;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.characteristics.Characteristics;

/**
 * Represents a Property in the property book.
 * Guarantees: field values are validated, immutable.
 * Only characteristics may be null.
 */
public class Property {

    // TODO: Remove description and tags fields

    // Identity fields
    private final PropertyName propertyName;
    private final Price price;

    // Data fields
    private final Address address;
    private final Description description;
    private final Optional<Characteristics> characteristics;
    private final Owner owner;
    private final LocalDateTime propertyEntryTime;

    /**
     * Every field must be present and not null.
     */
    public Property(PropertyName propertyName, Price price, Address address, Description description,
                    Characteristics characteristics, Owner owner, LocalDateTime propertyEntryTime) {
        requireAllNonNull(propertyName, price, address, description, owner, propertyEntryTime);
        this.propertyName = propertyName;
        this.price = price;
        this.address = address;
        this.description = description;
        this.characteristics = Optional.ofNullable(characteristics);
        this.owner = owner;
        this.propertyEntryTime = propertyEntryTime;
    }

    public PropertyName getPropertyName() {
        return propertyName;
    }

    public Address getAddress() {
        return address;
    }

    public Price getPrice() {
        return price;
    }

    public Description getDescription() {
        return description;
    }

    public LocalDateTime getPropertyEntryTime() {
        return propertyEntryTime;
    }

    public Owner getOwner() {
        return owner;
    }

    public Name getOwnerName() {
        return owner.getName();
    }

    public Phone getOwnerPhone() {
        return owner.getPhone();
    }

    public Optional<Characteristics> getCharacteristics() {
        return this.characteristics;
    }

    /**
     * Returns true if both properties have the same address.
     * This defines a weaker notion of equality between two properties.
     */
    public boolean isSameProperty(Property otherProperty) {
        if (otherProperty == this) {
            return true;
        }

        return otherProperty != null
                && otherProperty.getAddress().equals(getAddress());
    }

    /**
     * Returns true if both properties have the same identity and data fields.
     * This defines a stronger notion of equality between two properties.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Property)) {
            return false;
        }

        Property otherProperty = (Property) other;
        return otherProperty.getPropertyName().equals(getPropertyName())
                && otherProperty.getPrice().equals(getPrice())
                && otherProperty.getAddress().equals(getAddress())
                && otherProperty.getDescription().equals(getDescription())
                && otherProperty.getCharacteristics().equals(getCharacteristics())
                && otherProperty.getOwner().equals(getOwner());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(propertyName, price, address, description, characteristics, owner);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getPropertyName())
                .append("; Address: ")
                .append(getAddress())
                .append("; Price: ")
                .append(getPrice())
                .append("; Description: ")
                .append(getDescription());

        builder.append("; Characteristics: ")
                .append(getCharacteristics()
                        .map(Characteristics::toString)
                        .orElse("Not Specified"));

        builder.append("; Owner: ")
                .append(getOwner());
        return builder.toString();
    }

}
