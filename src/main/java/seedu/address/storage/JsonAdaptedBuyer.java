package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.address.Address;
import seedu.address.model.buyer.Buyer;
import seedu.address.model.buyer.Email;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.characteristics.Characteristics;
import seedu.address.model.pricerange.PriceRange;

/**
 * Jackson-friendly version of {@link Buyer}.
 */
class JsonAdaptedBuyer {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Buyer's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    // priceRange and desiredCharacteristics cannot be null; converted to "" for saving to storage if null
    private final String priceRange;
    private final String desiredCharacteristics;
    private final String specifiedPriority;
    private final String entryTime;

    /**
     * Constructs a {@code JsonAdaptedBuyer} with the given buyer details.
     */
    @JsonCreator
    public JsonAdaptedBuyer(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                            @JsonProperty("email") String email, @JsonProperty("address") String address,
                            @JsonProperty("priceRange") String priceRange,
                            @JsonProperty("desiredCharacteristics") String desiredCharacteristics,
                            @JsonProperty("priority") String specifiedPriority,
                            @JsonProperty("entryTime") String entryTime) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.priceRange = priceRange;
        this.entryTime = entryTime;
        this.desiredCharacteristics = desiredCharacteristics;
        this.specifiedPriority = specifiedPriority;
    }

    /**
     * Converts a given {@code Buyer} into this class for Jackson use.
     */
    public JsonAdaptedBuyer(Buyer source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        priceRange = source.getPriceRange().map(PriceRange::toString).orElse("");
        desiredCharacteristics = source.getDesiredCharacteristics()
                .map(Characteristics::toString)
                .orElse("");
        specifiedPriority = source.getPriority().toString();
        entryTime = source.getEntryTime().toString();
    }

    /**
     * Converts this Jackson-friendly adapted buyer object into the model's {@code Buyer} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted buyer.
     */
    public Buyer toModelType() throws IllegalValueException {

        if (entryTime == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    "entry time not found"));
        }

        final LocalDateTime modelTime = LocalDateTime.parse(entryTime);

        if (specifiedPriority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(specifiedPriority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(specifiedPriority);

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (priceRange == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PriceRange.class.getSimpleName()));
        }
        if (!priceRange.isEmpty() && !PriceRange.isValidPriceRange(priceRange)) {
            throw new IllegalValueException(PriceRange.MESSAGE_CONSTRAINTS);
        }
        final PriceRange modelPriceRange = priceRange.isEmpty() ? null : new PriceRange(priceRange);

        if (desiredCharacteristics == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Characteristics.class.getSimpleName()));
        }
        if (!desiredCharacteristics.isEmpty()
                && !Characteristics.isValidCharacteristics(desiredCharacteristics)) {
            throw new IllegalValueException(Characteristics.MESSAGE_CONSTRAINTS);
        }
        final Characteristics modelCharacteristics = desiredCharacteristics.isEmpty()
                ? null
                : new Characteristics(desiredCharacteristics);


        return new Buyer(modelName, modelPhone, modelEmail, modelAddress,
                modelPriceRange, modelCharacteristics, modelPriority, modelTime);
    }
}
