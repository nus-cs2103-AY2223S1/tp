package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Name;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.property.Description;
import seedu.address.model.property.Owner;
import seedu.address.model.price.Price;
import seedu.address.model.property.Property;
import seedu.address.model.property.PropertyName;


/**
 * Jackson-friendly version of {@link Property}.
 */
class JsonAdaptedProperty {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Property's %s field is missing!";

    private final String propertyName;
    private final String price;
    private final String address;
    private final String description;
    // characteristics cannot be null; converted to "" for saving to storage if null
    private final String characteristics;
    private final String entryTime;
    private final JsonAdaptedOwner owner;

    /**
     * Constructs a {@code JsonAdaptedProperty} with the given property details.
     */
    @JsonCreator
    public JsonAdaptedProperty(@JsonProperty("name") String propertyName, @JsonProperty("price") String price,
                               @JsonProperty("address") String address, @JsonProperty("description") String description,
                               @JsonProperty("characteristics") String characteristics,
                               @JsonProperty("owner") JsonAdaptedOwner owner,
                               @JsonProperty("entryTime") String entryTime) {
        this.propertyName = propertyName;
        this.price = price;
        this.address = address;
        this.description = description;
        this.characteristics = characteristics;
        this.entryTime = entryTime;
        this.owner = owner;
    }

    /**
     * Converts a given {@code Property} into this class for Jackson use.
     */
    public JsonAdaptedProperty(Property source) {
        propertyName = source.getPropertyName().fullName;
        price = source.getPrice().value;
        address = source.getAddress().value;
        description = source.getDescription().value;
        entryTime = source.getPropertyEntryTime().toString();
        characteristics = source.getCharacteristics()
                .map(Characteristics::toString)
                .orElse("");
        owner = new JsonAdaptedOwner(source.getOwner());
    }

    /**
     * Converts this Jackson-friendly adapted property object into the model's {@code Property} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted property.
     */
    public Property toModelType() throws IllegalValueException {

        if (entryTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "entry time not found"));
        }

        final LocalDateTime modelTime = LocalDateTime.parse(entryTime);

        if (propertyName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PropertyName.class.getSimpleName()));
        }
        if (!PropertyName.isValidPropertyName(propertyName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final PropertyName modelPropertyName = new PropertyName(propertyName);

        if (price == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Price.class.getSimpleName()));
        }
        if (!Price.isValidPrice(price)) {
            throw new IllegalValueException(Price.MESSAGE_CONSTRAINTS);
        }
        final Price modelPrice = new Price(price);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDescription = new Description(propertyName);

        if (characteristics == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Characteristics.class.getSimpleName()));
        }
        if (!characteristics.isEmpty()
                && !Characteristics.isValidCharacteristics(characteristics)) {
            throw new IllegalValueException(Characteristics.MESSAGE_CONSTRAINTS);
        }
        final Characteristics modelCharacteristics = characteristics.isEmpty()
                ? null
                : new Characteristics(characteristics);

        if (owner == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Owner.class.getSimpleName()));
        }
        final Owner modelOwner = owner.toModelType();

        return new Property(modelPropertyName, modelPrice, modelAddress, modelDescription,
                modelCharacteristics, modelOwner, modelTime);
    }
}
