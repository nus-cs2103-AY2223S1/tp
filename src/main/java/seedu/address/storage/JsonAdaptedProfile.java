package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.profile.Address;
import seedu.address.model.profile.Email;
import seedu.address.model.profile.Name;
import seedu.address.model.profile.Phone;
import seedu.address.model.profile.Profile;

/**
 * Jackson-friendly version of {@link Profile}.
 */
class JsonAdaptedProfile {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Profile's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;

    /**
     * Constructs a {@code JsonAdaptedProfile} with the given profile details.
     */
    @JsonCreator
    public JsonAdaptedProfile(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
            @JsonProperty("email") String email, @JsonProperty("address") String address) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
    }

    /**
     * Converts a given {@code Profile} into this class for Jackson use.
     */
    public JsonAdaptedProfile(Profile source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
    }

    /**
     * Converts this Jackson-friendly adapted profile object into the model's {@code Profile} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted profile.
     */
    public Profile toModelType() throws IllegalValueException {
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

        return new Profile(modelName, modelPhone, modelEmail, modelAddress);
    }

}
