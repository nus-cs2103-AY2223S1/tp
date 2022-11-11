package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.buyer.Name;
import seedu.address.model.buyer.Phone;
import seedu.address.model.buyer.Priority;
import seedu.address.model.property.Owner;

/**
 * Jackson-friendly version of {@link Owner}.
 */
class JsonAdaptedOwner {

    private final String name;
    private final String phone;

    /**
     * Constructs a {@code JsonAdaptedOwner} with the given {@code name} and {@code phone}.
     */
    @JsonCreator
    public JsonAdaptedOwner(@JsonProperty("name") String name, @JsonProperty("phone") String phone) {
        this.name = name;
        this.phone = phone;
    }

    /**
     * Converts a given {@code Owner} into this class for Jackson use.
     */
    public JsonAdaptedOwner(Owner source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Owner toModelType() throws IllegalValueException {
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        return new Owner(new Name(name), new Phone(phone));
    }

}
